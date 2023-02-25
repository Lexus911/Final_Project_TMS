package com.example.teachmenotes.presentation.notes.adapter

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.teachmenotes.databinding.ColorsListBinding
import com.example.teachmenotes.presentation.model.ColorModel
import com.example.teachmenotes.presentation.notes.adapter.listener.ColorsListener
import com.example.teachmenotes.utils.BundleConstants.COLOR_VALUE

class ColorsViewHolder (private val binding: ColorsListBinding,
                        private var colorsListener: ColorsListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(colorModel: ColorModel){

        binding.colorsContainer.setCardBackgroundColor(Color.parseColor(colorModel.value))

        binding.colorsContainer.setOnClickListener {

            Log.w("color view holder", "${colorModel.value}")
            colorsListener.onClick(colorModel.value)
            val bundle = Bundle()
            bundle.putString(COLOR_VALUE, colorModel.value)
        }

    }
}