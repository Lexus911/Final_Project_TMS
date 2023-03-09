package com.example.teachmenotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.FragmentViewPagerBinding
import com.example.teachmenotes.presentation.notes.adapter.ViewPagerStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment()  {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragmentAdapter: ViewPagerStateAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentAdapter = ViewPagerStateAdapter(this)
        binding.viewPager.adapter = fragmentAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabIcons: Array<Int> = arrayOf(
                R.drawable.ic_notes_bottom_nav,
                R.drawable.ic_tasks_bottom_nav,
            )
            tab.setIcon(tabIcons[position])
        }.attach()
    }
}