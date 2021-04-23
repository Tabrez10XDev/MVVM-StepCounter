package com.lj.bwow.viewmodel

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.*
import com.lj.bwow.data.HealthResponse
import com.lj.bwow.data.room.Data
import com.lj.bwow.data.room.Steps
import com.lj.bwow.repositories.HealthRepository
import com.lj.bwow.util.Resource
import com.lj.bwow.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HealthRepository,
    ) : ViewModel() {




    val healthData = repository.observeHealthData()

    val stepCount = repository.observeStepCount()


    private fun insertHealthDataIntoDb(healthData: Data) = viewModelScope.launch {
        repository.insertHealthData(healthData)
    }

    private fun insertHealthData(healthData: Resource<HealthResponse>){
        when(healthData.status){
            Status.LOADING -> {
                // NO OP
            }
            Status.SUCCESS ->{
                Log.d("LJ",healthData.data.toString())
                healthData.data?.let { insertHealthDataIntoDb(it.data) }
            }
            Status.ERROR -> {
                Log.d("LJ","Error")

                // TODO Show Error message
            }
        }
    }

    fun insertStepCount(stepCount: Steps) = viewModelScope.launch {
        repository.insertStepCount(stepCount)
    }



    fun fetchHealthData(){
        viewModelScope.launch {
            try {
              //  if(hasInternetConnection()) {
                    val response = repository.fetchHealthData()
                    insertHealthData(response)
                //}
            }
            catch (t : Throwable){
                //TODO
            }

        }
    }

//    fun subscribeToActions(){
//        val transitions = mutableListOf<ActivityTransition>()
//
//        transitions +=
//            ActivityTransition.Builder()
//                .setActivityType(DetectedActivity.WALKING)
//                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
//                .build()
//
//        transitions +=
//            ActivityTransition.Builder()
//                .setActivityType(DetectedActivity.RUNNING)
//                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
//                .build()
//
//        val request = ActivityTransitionRequest(transitions)
//
//        task.requestActivityTransitionUpdates(request, Intent())
//
//
//        task.addOnSuccessListener {
//            // Handle success
//        }
//
//        task.addOnFailureListener { e: Exception ->
//            // Handle error
//        }
//
//    }



//    private fun hasInternetConnection(): Boolean{
//        if (connectivityManager != null) {
//            val capabilities =
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                when {
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                        return true
//                    }
//                }
//            }
//        }
//        return false
//
//    }

}