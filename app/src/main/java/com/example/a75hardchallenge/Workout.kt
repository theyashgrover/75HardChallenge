package com.example.a75hardchallenge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a75hardchallenge.databinding.FragmentWorkoutBinding

class Workout: Fragment(R.layout.fragment_workout) {
    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)

        binding.indoorWorkout.setOnClickListener {
            val intent = Intent(activity , ExerciseActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}