package com.example.a75hardchallenge.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a75hardchallenge.Activities.SignInActivity
import com.example.a75hardchallenge.R
import com.example.a75hardchallenge.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class Profile: Fragment(R.layout.fragment_profile) {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(layoutInflater,container,false)
        val view=binding.root
        binding.aboutTV.setOnClickListener {
            firebaseAuth.signOut()
            val intent=Intent(activity,SignInActivity::class.java)

            startActivity(intent)
        }
        return view

    }
}