package com.lj.bwow.app

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.lj.bwow.R
import com.lj.bwow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.jar.Manifest

@AndroidEntryPoint
class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermission()
    }

    private fun hasActivityRecognitionPermission() : Boolean{
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(){
        var permissionToRequest = mutableListOf<String>()
        if(!hasActivityRecognitionPermission()){
            permissionToRequest.add(android.Manifest.permission.ACTIVITY_RECOGNITION)
        }

        if (permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("Permission Requests", grantResults[i].toString())
                }
            }
        }
    }
}