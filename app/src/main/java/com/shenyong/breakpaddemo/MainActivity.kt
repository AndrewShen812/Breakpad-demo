package com.shenyong.breakpaddemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("Breakpad")
            System.loadLibrary("Crash-lib")
        }
    }

    /**
     * native method
     */
    external fun breakpadInit(path: String)

    external fun genCrash(): String

    private var dumpPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1008)
            } else {
                initPath()
            }
        } else {
            initPath()
        }

        btn_crash.setOnClickListener {
            breakpadInit(dumpPath)
            genCrash()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        initPath()
    }

    private fun initPath() {
        val dir: File = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(Environment.getExternalStorageDirectory(), "crashDump")
        } else {
            File(filesDir.parent, "crashDump")
        }
        if (!dir.exists()) {
            dir.mkdir()
        }
        dumpPath = dir.absolutePath
    }
}
