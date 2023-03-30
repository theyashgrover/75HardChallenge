package com.example.a75hardchallenge

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a75hardchallenge.databinding.ActivityExerciseBinding
import com.example.a75hardchallenge.databinding.ItemExerciseStatusBinding


class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>,val context : Context) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(items[position]){
                if (items[position].getIsSelected()) {
                    binding.tvItem.background = ContextCompat.getDrawable(
                        context,
                        R.drawable.item_circular_thin_color_accent_border
                    )
                    binding.tvItem.setTextColor(Color.parseColor("#212121"))
                } else if (items[position].getIsCompleted()) {
                    binding.tvItem.background =
                        ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
                    binding.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
                }
                else{
                    binding.tvItem.background =
                        ContextCompat.getDrawable(context, R.drawable.item_circular_color_gray_background)
                    binding.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }
        }

       


    }
    override fun getItemCount(): Int {
        return items.size
    }
}

