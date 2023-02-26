package com.example.teachmenotes.presentation.notes.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.databinding.ColorsListBinding
import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.notes.adapter.listener.ColorsListener

class ColorsViewHolder (private val binding: ColorsListBinding,
                        private var colorsListener: ColorsListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(colorModel: ColorModel){

        binding.colorsContainer.setCardBackgroundColor(Color.parseColor(colorModel.value))

        binding.colorsContainer.setOnClickListener {
            colorsListener.onClick(colorModel.value)
        }

    }
}