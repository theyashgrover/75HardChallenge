package com.example.a75hardchallenge.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a75hardchallenge.databinding.ActivityInsideExerciseBinding
//import com.example.a75hardchallenge.databinding.ActivityOutsideExerciseBinding

class InsideExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsideExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInsideExerciseBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.pushWorkoutCard.setOnClickListener {
            val intent= Intent(this,PushWorkoutActivity::class.java)
            startActivity(intent)
        }
        binding.pullWorkoutCard.setOnClickListener {
            val intent=Intent(this,PullWorkoutActivity::class.java)
            startActivity(intent)
        }
        binding.legWorkoutCard.setOnClickListener {
            val intent=Intent(this,LegWorkoutActivity::class.java)
            startActivity(intent)
        }
    }
}