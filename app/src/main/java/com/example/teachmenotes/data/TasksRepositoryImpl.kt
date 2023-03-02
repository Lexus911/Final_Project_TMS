package com.example.teachmenotes.data

import com.example.teachmenotes.data.database.TasksEntity
import com.example.teachmenotes.data.database.dao.NotesDAO
import com.example.teachmenotes.domain.TasksRepository
import com.example.teachmenotes.presentation.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val notesDAO: NotesDAO
    ): TasksRepository{
    override suspend fun saveTask(taskModel: TaskModel) {
        withContext(Dispatchers.IO) {
            notesDAO.insertTasksEntity(
                TasksEntity(
                    taskModel.id,
                    taskModel.task,
                    taskModel.completed
                )
            )
        }
    }

    override suspend fun showTasks(): Flow<List<TaskModel>> {
        return withContext(Dispatchers.IO) {
            val tasksEntity = notesDAO.getTasksEntities()
            tasksEntity.map {tasksList ->
               tasksList.map{
                    TaskModel(
                        it.id,
                        it.task,
                        it.completed,
                    )
                }
            }
        }
    }

    override suspend fun deleteTaskById(id: Int) {
        withContext(Dispatchers.IO) {
            notesDAO.deleteTaskEntityById(id)
        }
    }

    override suspend fun saveEditTask(task: String, id: Int) {
        withContext(Dispatchers.IO) {
            notesDAO.saveEditTask(task, id)
        }
    }

}