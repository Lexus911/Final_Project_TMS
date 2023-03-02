package com.example.teachmenotes.domain

import com.example.teachmenotes.presentation.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksInteractor @Inject constructor(private val tasksRepository: TasksRepository) {

    suspend fun saveTask(taskModel: TaskModel){
        return tasksRepository.saveTask(taskModel)
    }

    suspend fun showTasks(): Flow<List<TaskModel>>{
        return tasksRepository.showTasks()
    }

    suspend fun deleteTaskById(id: Int){
        return tasksRepository.deleteTaskById(id)
    }

    suspend fun saveEditTask(task: String, id: Int){
        return tasksRepository.saveEditTask(task, id)
    }


}