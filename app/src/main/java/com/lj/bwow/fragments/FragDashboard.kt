package com.lj.bwow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lj.bwow.R
import com.lj.bwow.databinding.FragmentDashboardBinding
import com.lj.bwow.viewmodel.MainViewModel
import javax.inject.Inject


class FragDashboard : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

    }


}