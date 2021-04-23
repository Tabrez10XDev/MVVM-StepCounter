package com.lj.bwow.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class MainFragmentFactory @Inject constructor() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            FragDashboard::class.java.name -> FragDashboard()
            else -> return super.instantiate(classLoader, className)
        }
    }
}