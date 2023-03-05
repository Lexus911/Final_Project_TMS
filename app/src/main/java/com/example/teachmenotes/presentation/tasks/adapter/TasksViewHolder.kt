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
        binding.checkBox.isChecked = taskModel.completed

        binding.tasksContainer.setOnLongClickListener {
            tasksListener.onLongClick(taskModel, binding.tasksContainer)
            true
        }

        binding.tasksContainer.setOnClickListener {
            tasksListener.onClick(taskModel)
        }


        when (binding.checkBox.isChecked) {
            true -> binding.textViewTask.paintFlags =
                binding.textViewTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            false -> binding.textViewTask.paintFlags =
                binding.textViewTask.paintFlags and (Paint.STRIKE_THRU_TEXT_FLAG.inv())
        }


        binding.checkBox.setOnClickListener {
            if (!taskModel.completed) {
                binding.textViewTask.paintFlags =
                    binding.textViewTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                taskModel.completed = binding.checkBox.isChecked
                tasksListener.setCompleted(binding.checkBox.isChecked, taskModel.id!!)

            } else {
                binding.textViewTask.paintFlags =
                    binding.textViewTask.paintFlags and (Paint.STRIKE_THRU_TEXT_FLAG.inv())

                taskModel.completed = binding.checkBox.isChecked
                tasksListener.setCompleted(binding.checkBox.isChecked, taskModel.id!!)
            }
        }
    }
}