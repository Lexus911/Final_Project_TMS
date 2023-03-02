package com.example.teachmenotes.presentation.tasks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.databinding.TasksListBinding
import com.example.teachmenotes.presentation.model.TaskModel
import com.example.teachmenotes.presentation.tasks.adapter.listener.TasksListener

class TasksViewHolder(
    private val binding: TasksListBinding,
    private var tasksListener: TasksListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(taskModel: TaskModel) {

        binding.textViewTask.text = taskModel.task

        binding.tasksContainer.setOnLongClickListener {
            tasksListener.onLongClick(taskModel, binding.tasksContainer)
            true
        }

    }


}