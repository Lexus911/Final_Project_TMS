package com.example.teachmenotes.presentation.tasks.adapter

import android.graphics.Paint
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

        binding.tasksContainer.setOnClickListener {
            tasksListener.onClick(taskModel)
        }

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.textViewTask.paintFlags =
                    binding.textViewTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding.textViewTask.paintFlags =
                    binding.textViewTask.paintFlags and (Paint.STRIKE_THRU_TEXT_FLAG.inv())
            }

        }

    }
}