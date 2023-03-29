package com.example.a75hardchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a75hardchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val browser_Fragment= Browser()
        val profile_fragment=Profile()
        val reminder_fragment=Reminder()
        val rules_fragment=Rules()
        val workout_fragment=Workout()


        setCurrentFragment(workout_fragment)
        binding.bottomNavigationView.selectedItemId=R.id.workout
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.workout->setCurrentFragment(workout_fragment)
                R.id.browser->setCurrentFragment(browser_Fragment)
                R.id.profile->setCurrentFragment(profile_fragment)
                R.id.alarm->setCurrentFragment(reminder_fragment)
                R.id.tasks->setCurrentFragment(rules_fragment)
            }
            true
        }




    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_icon-> Toast.makeText(this,"YOUR PROFILE", Toast.LENGTH_SHORT).show()
            R.id.user_settings-> Toast.makeText(this,"user settings", Toast.LENGTH_SHORT).show()

        }
        return true
    }
    // we create a function to replace fragments
    // we will add new fragments into our fragment container i.e--> fragment_container
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment) //here fragment is our parameter defining which fragment will go inside our container
            commit()
        }
}