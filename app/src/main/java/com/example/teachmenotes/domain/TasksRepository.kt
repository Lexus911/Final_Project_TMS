package com.example.teachmenotes.domain

import com.example.teachmenotes.presentation.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun saveTask(taskModel: TaskModel)

    suspend fun showTasks(): Flow<List<TaskModel>>

    suspend fun deleteTaskById(id: Int)

    suspend fun saveEditTask(task: String, id: Int)

    suspend fun setCompleted(completed: Boolean, id: Int)
}