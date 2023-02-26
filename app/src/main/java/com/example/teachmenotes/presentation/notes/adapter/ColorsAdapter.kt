package com.example.teachmenotes.presentation.notes.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.databinding.ColorsListBinding
import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.model.NoteModel
import com.example.teachmenotes.presentation.notes.adapter.listener.ColorsListener

class ColorsAdapter (private var colorsListener: ColorsListener): RecyclerView.Adapter<ColorsViewHolder>() {
    private var listColors = listOf<ColorModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ColorModel>){

        this.listColors = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val binding = ColorsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorsViewHolder(binding, colorsListener)
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        holder.bind(listColors[position])


    }

    override fun getItemCount(): Int {
        return listColors.size
    }


}