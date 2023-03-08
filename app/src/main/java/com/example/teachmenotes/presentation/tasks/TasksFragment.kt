package com.example.teachmenotes.presentation.tasks

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachmenotes.R
import com.example.teachmenotes.databinding.FragmentTasksBinding
import com.example.teachmenotes.presentation.model.TaskModel
import com.example.teachmenotes.presentation.tasks.adapter.TasksAdapter
import com.example.teachmenotes.presentation.tasks.adapter.listener.TasksListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch

@AndroidEntryPoint
class TasksFragment : Fragment(), TasksListener {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    private lateinit var tasksAdapter: TasksAdapter
    private val viewModel: TasksViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksAdapter = TasksAdapter(this)
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTasks.adapter = tasksAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.tasks.catch {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
                .collect { flowList ->
                    flowList.collect { listTasks ->
                        tasksAdapter.submitList(listTasks)
                    }
                }
        }
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        binding.btnAddTask.setOnClickListener {

            val dialog = AlertDialog.Builder(requireContext())
            val dialogView = layoutInflater.inflate(R.layout.alert_dialog, null)

            dialog.setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok_alert_dialog)) { _, _ ->
                    viewModel.saveTask(
                        TaskModel(
                            null,
                            dialogView.findViewById<EditText>(R.id.edit_text_alert_dialog).text.toString(),
                            false
                        )
                    )
                }
                .setNegativeButton(getString(R.string.cancel_alert_dialog)) { dialog, _ ->
                    dialog.cancel()
                }
            dialog.show()
        }

    }

    override fun onClick(taskModel: TaskModel) {

        val dialog = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog, null)
        val editTextDialog = dialogView.findViewById<EditText>(R.id.edit_text_alert_dialog)
        editTextDialog.setText(taskModel.task)

        dialog.setView(dialogView)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok_alert_dialog)) { _, _ ->
                    viewModel.saveEditTask(
                        editTextDialog.text.toString(),
                        taskModel.id!!
                    )
            }
            .setNegativeButton(getString(R.string.cancel_alert_dialog)) { dialog, _ ->
                dialog.cancel()
            }
        dialog.show()
    }

    override fun onLongClick(taskModel: TaskModel, cardView: CardView) {
        val popupMenu = PopupMenu(requireContext(), cardView)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_note -> {
                    viewModel.deleteTask(taskModel.id!!)
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.task_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }

    override fun setCompleted(completed: Boolean, id: Int) {
        viewModel.setCompleted(completed,id)
    }
}
