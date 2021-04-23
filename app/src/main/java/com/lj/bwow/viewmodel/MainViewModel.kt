package com.lj.bwow.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lj.bwow.data.HealthResponse
import com.lj.bwow.data.room.Data
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


    private fun insertHealthDataIntoDb(healthData: Data) = viewModelScope.launch {
        repository.insertHealthData(healthData)
    }

    private fun insertHealthData(healthData: Resource<HealthResponse>){
        when(healthData.status){
            Status.LOADING -> {
                // NO OP
            }
            Status.SUCCESS ->{
                healthData.data?.let { insertHealthDataIntoDb(it.data) }
            }
            Status.ERROR -> {
                // TODO Show Error message
            }
        }
    }


    fun fetchHealthData(){
        viewModelScope.launch {
            try {
                val response = repository.fetchHealthData()
                insertHealthData(response)
            }
            catch (t : Throwable){
                //TODO
            }

        }
    }



    private fun hasInternetConnection(context : Context): Boolean{
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        }
        return false

    }

}