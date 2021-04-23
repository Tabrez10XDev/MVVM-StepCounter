package com.lj.bwow.fragments

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lj.bwow.R
import com.lj.bwow.databinding.FragmentDashboardBinding
import com.lj.bwow.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragDashboard : Fragment(), SensorEventListener {
    private lateinit var binding: FragmentDashboardBinding

    lateinit var viewModel: MainViewModel

    private var i : Int = 0
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        setupPedometer()

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.healthData.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                Log.d("LJ",it.heartRate)
                binding.tvPurple.text = it.sleepTime + "Hours"
                binding.tvRed.text = it.heartRate
                binding.tvGreen.text = it.trainingTime
            }
        })

        binding.button.setOnClickListener {
            viewModel.fetchHealthData()
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST)

    }

    override fun onPause() {
        super.onPause()
        mSensorManager?.unregisterListener(this);

    }

    private fun setupPedometer(){
        mSensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        if(mSensorManager!=null) {
            mAccelerometer = mSensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        Log.d("Shami","Sham")
        // Data 1: According to official documentation, the first value of the `SensorEvent` value is the step count
        event.values.firstOrNull()?.let {
            i +=1
            binding.tvBlue.text = i.toString()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


}