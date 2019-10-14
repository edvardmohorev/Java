
package com.example.storage.activity

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build

import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

import com.example.storage.R
import java.io.File

/**
 * The main [Activity] for the Drive API migration sample app.
 */
class MainActivity : AppCompatActivity() {
//    lateinit var nameFile:String
//    lateinit var textFile:String
//    lateinit var client:GoogleSignInClient
//    lateinit var sing:Button
//
//    private var mDriveServiceHelper: DriveServiceHelper? = null
//
//
//    private lateinit var mDocContentEditText: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isStoragePermission()
        val sdPath = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)
        val folder=File(sdPath.path+"/here")
        folder.mkdirs()

    }
    fun isStoragePermission():Boolean{
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                return true
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
                return false
            }
        }
        else{
            return true
        }
    }
    }


