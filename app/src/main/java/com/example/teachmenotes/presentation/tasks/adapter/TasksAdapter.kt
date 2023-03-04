package com.example.teachmenotes.presentation.tasks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.databinding.TasksListBinding
import com.example.teachmenotes.presentation.model.TaskModel
import com.example.teachmenotes.presentation.tasks.adapter.listener.TasksListener

class TasksAdapter(
    private var tasksListener: TasksListener,
): RecyclerView.Adapter<TasksViewHolder>() {

    private var listTasks = listOf<TaskModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<TaskModel>){

        this.listTasks = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = TasksListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding, tasksListener)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(listTasks[position])
    }

    override fun getItemCount(): Int {
        return listTasks.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}