package com.mvclopes.wtest

import android.Manifest
import android.app.DownloadManager
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.File

private const val PERMISSION_REQUESTED = Manifest.permission.READ_EXTERNAL_STORAGE
private const val URL_CSV_FILE = "https://raw.githubusercontent.com/centraldedados/codigos_postais/master/data/codigos_postais.csv"
private const val CSV_FILE_NAME = "cod-postais.csv"

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
        val listFiles: Array<out File>? = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .listFiles()

        val fileAlreadyExists: List<File>? =
            if (listFiles.isNullOrEmpty()) null
            else listFiles.filter { it.name == CSV_FILE_NAME }

        if (fileAlreadyExists.isNullOrEmpty()) {
            downloadCSV()
        }
    }

    private fun downloadCSV() {
        val request = DownloadManager.Request(Uri.parse(URL_CSV_FILE))
            .setTitle("codigos-postais")
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, CSV_FILE_NAME)
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

}