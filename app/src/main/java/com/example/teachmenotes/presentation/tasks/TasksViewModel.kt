package com.example.teachmenotes.presentation.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachmenotes.domain.TasksInteractor
import com.example.teachmenotes.presentation.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksInteractor: TasksInteractor
): ViewModel() {


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val tasks = flow<Flow<List<TaskModel>>>{ emit(tasksInteractor.showTasks()) }

    fun saveTask(taskModel: TaskModel) {
        viewModelScope.launch {
            try {
                tasksInteractor.saveTask(taskModel)
            }catch (e: Exception){
                _error.value = e.message.toString()
            }
        }
    }

    fun saveEditTask(task: String, id: Int) {
        viewModelScope.launch {
            try {
                tasksInteractor.saveEditTask(task, id)
            }catch (e: Exception){
                _error.value = e.message.toString()
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            tasksInteractor.deleteTaskById(id)
        }

    }

}