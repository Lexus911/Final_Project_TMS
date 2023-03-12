package com.example.teachmenotes.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.FragmentSplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSplashScreen.alpha = 0f
        binding.tvSplashScreen.animate().setDuration(1300).alpha(1f)

        binding.imageSplashScreen.alpha = 0f
        binding.imageSplashScreen.animate().setDuration(1300).alpha(1f).withEndAction {
        val navOptions = NavOptions.Builder()
        navOptions.setPopUpTo(R.id.splashScreenFragment, true)
        findNavController().navigate(
            R.id.action_splashScreenFragment_to_viewPagerFragment,
            null,
            navOptions.build())
        }
    }


}