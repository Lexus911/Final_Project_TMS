package com.example.teachmenotes.presentation.tasks.adapter.listener

import androidx.cardview.widget.CardView
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.model.TaskModel

interface TasksListener {

    fun onClick(taskModel: TaskModel)

    fun onLongClick(taskModel: TaskModel, cardView: CardView)

    fun setCompleted(completed: Boolean, id: Int)
}