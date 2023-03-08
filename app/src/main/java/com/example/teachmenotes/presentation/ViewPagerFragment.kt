package com.example.teachmenotes.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.FragmentViewPagerBinding
import com.example.teachmenotes.presentation.notes.NotesFragment
import com.example.teachmenotes.presentation.notes.adapter.ViewPagerStateAdapter
import com.google.android.material.tabs.TabLayout
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

        binding.tabButtonMenu.setOnClickListener {
            showPopUpMenu()
        }


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
            if(tab.position == 1){
                binding.tabButtonMenu.visibility = GONE
            }else{
                binding.tabButtonMenu.visibility = VISIBLE
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    private fun showPopUpMenu(){
        val popupMenu = PopupMenu(requireContext(), binding.tabButtonMenu)
        popupMenu.inflate(R.menu.tab_menu)
        popupMenu.setOnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.list_display -> {
                    val activity = activity as MainActivity
                    val fragment = activity.supportFragmentManager.findFragmentById(R.id.notesFragment)
                    if (fragment is NotesFragment) {
                        fragment.setLinearLayout()
                    }

                    true
                }
                R.id.columns_display -> {
//                    NotesFragment().setGridLayout()
                    true
                }
                R.id.sorting_ASC -> {

                    true
                }
                R.id.sorting_DESC -> {

                    true
                }
                else -> false
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }


}