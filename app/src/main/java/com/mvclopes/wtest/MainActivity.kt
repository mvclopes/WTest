package com.mvclopes.wtest

import android.Manifest
import android.app.DownloadManager
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mvclopes.wtest.utils.CSV_DIR_NAME
import com.mvclopes.wtest.utils.CSV_FILE_NAME
import com.mvclopes.wtest.utils.URL_CSV_FILE

private const val PERMISSION_REQUESTED = Manifest.permission.READ_EXTERNAL_STORAGE

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
        if (isGranted) verifyFile()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyPermission()
    }

    private fun verifyPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                PERMISSION_REQUESTED
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            verifyFile()
        } else {
            requestPermissionLauncher.launch(PERMISSION_REQUESTED)
        }
    }

    private fun verifyFile() {
        val fileAlreadyExists = getExternalFilesDir(CSV_DIR_NAME)
            ?.listFiles()
            .isNullOrEmpty()
            .not()

        if (!fileAlreadyExists) {
            downloadCSV()
        }
    }

    private fun downloadCSV() {
        val request = DownloadManager.Request(Uri.parse(URL_CSV_FILE))
            .setTitle(CSV_FILE_NAME)
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setDestinationInExternalFilesDir(this, CSV_DIR_NAME, CSV_FILE_NAME)
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

}