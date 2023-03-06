package com.example.teachmenotes.presentation.notes.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teachmenotes.presentation.notes.NotesFragment
import com.example.teachmenotes.presentation.tasks.TasksFragment

class ViewPagerStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return if(position == 0){
            NotesFragment()
        }else{
            TasksFragment()
        }
    }

    override fun getItemCount(): Int = 2

}