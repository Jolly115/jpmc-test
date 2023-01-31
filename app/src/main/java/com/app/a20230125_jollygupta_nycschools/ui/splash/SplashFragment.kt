package com.app.a20230125_jollygupta_nycschools.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.a20230125_jollygupta_nycschools.R
import com.app.a20230125_jollygupta_nycschools.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding :FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSplashBinding.inflate(inflater,container,false)

        //Add Handler for staying at splash screen for 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val navigationAction = SplashFragmentDirections.actionToHomeFragment()
            findNavController().navigate(navigationAction)
        },3000)
        return binding.root
    }
}