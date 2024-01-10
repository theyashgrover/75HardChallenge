package com.example.a75hardchallenge.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a75hardchallenge.Data.UserData
import com.example.a75hardchallenge.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    //for firbase authorisation
    private lateinit var firebaseAuth: FirebaseAuth
    //for database reference
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        firebaseAuth=FirebaseAuth.getInstance()
        setContentView(binding.root)
        //getting/assigning uid to user
        val uid=firebaseAuth.currentUser?.uid
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")



        binding.signInTV.setOnClickListener {
            val intent=Intent(this,SignInActivity::class.java)
            startActivity(intent)

        }

        binding.SignUpbutton.setOnClickListener {
            val email=binding.emailEt.text.toString()
            val pass=binding.passET.text.toString()
            val confirmPass=binding.confirmPassEt.text.toString()
            val name=binding.nameEt.text.toString()
            val user=UserData(userName = name)
            if (email.isNotEmpty()&&pass.isNotEmpty()&&confirmPass.isNotEmpty()){
                if (pass==confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent=Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            if (uid!=null){
                                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                                    if (it.isSuccessful){
                                        Toast.makeText(this,"Uploaded",Toast.LENGTH_LONG).show()
                                        Log.d("UserUpdate","$name")
                                    }
                                    else{
                                        Toast.makeText(this,"Failed to Upload details",Toast.LENGTH_LONG).show()
                                    }
                                }

                            }

                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                    Toast.makeText(this,"Password is not matching",Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_LONG).show()
            }
        }
    }
}