package com.mvclopes.wtest.presentation

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.mvclopes.wtest.databinding.FragmentHomeBinding
import com.mvclopes.wtest.presentation.adapter.PostalCodeAdapter
import com.mvclopes.wtest.utils.CSV_DIR_NAME
import com.mvclopes.wtest.utils.CSV_FILE_NAME
import com.mvclopes.wtest.utils.URL_CSV_FILE
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val PERMISSION_REQUESTED = Manifest.permission.READ_EXTERNAL_STORAGE

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val adapter: PostalCodeAdapter by lazy { PostalCodeAdapter() }
    private val viewModel: HomeViewModel by viewModel()

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
        if (isGranted) verifyFile()
    }

    private var onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context?, intent: Intent?) {
            viewModel.isDatabaseEmpty()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        verifyPermission()
        onFinishedDownload()
        setRecyclerAdapter()
        observeState()

        return binding.root
    }

    private fun observeState() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.contentGroup.isVisible = isLoading.not()
        }
        viewModel.postalCodeList.observe(viewLifecycleOwner) { codes ->
            adapter.submitList(codes)
        }
    }

    private fun setRecyclerAdapter() {
        binding.postalCodeRecycler.adapter = adapter
    }

    private fun onFinishedDownload() {
        requireActivity().registerReceiver(
            onDownloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }


    private fun verifyPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                PERMISSION_REQUESTED
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            verifyFile()
        } else {
            requestPermissionLauncher.launch(PERMISSION_REQUESTED)
        }
    }

    private fun verifyFile() {
        val fileAlreadyExists = requireActivity().getExternalFilesDir(CSV_DIR_NAME)
            ?.listFiles()
            .isNullOrEmpty()
            .not()

        if (!fileAlreadyExists) downloadCSV() else viewModel.isDatabaseEmpty()
    }

    private fun downloadCSV() {
        val request = DownloadManager.Request(Uri.parse(URL_CSV_FILE))
            .setTitle(CSV_FILE_NAME)
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setDestinationInExternalFilesDir(requireContext(), CSV_DIR_NAME, CSV_FILE_NAME)
        val downloadManager = requireActivity().getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

}