package com.example.a75hardchallenge.Activities
//import android.os.Build.VERSION_CODES.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a75hardchallenge.Fragments.Browser
import com.example.a75hardchallenge.Fragments.Profile
import com.example.a75hardchallenge.Fragments.Reminder
import com.example.a75hardchallenge.Fragments.Rules
import com.example.a75hardchallenge.Fragments.Workout
import com.example.a75hardchallenge.R
import com.example.a75hardchallenge.RoomDB.Database.UserPreferredUIDatabase
import com.example.a75hardchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    //var firstExecuted=false
   // lateinit var userRepositoryProvider: UserRepositoryProvider
   lateinit var database: UserPreferredUIDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            // This is not the first launch, you can proceed with normal app flow.
            //   userRepositoryProvider = UserRepositoryProvider.getInstance(this)
            //firstExecuted=false
            database= UserPreferredUIDatabase.getDatabase(this)
            createNotificationChannel()
            val browser_Fragment= Browser()
            val profile_fragment= Profile()
            val reminder_fragment= Reminder()
            val rules_fragment= Rules()
            rules_fragment.setDatabaseInstance(database)
            val workout_fragment= Workout()


            setCurrentFragment(workout_fragment)
            binding.bottomNavigationView.selectedItemId= R.id.workout
            binding.bottomNavigationView.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.workout ->setCurrentFragment(workout_fragment)
                    R.id.browser ->setCurrentFragment(browser_Fragment)
                    R.id.profile ->setCurrentFragment(profile_fragment)
                    R.id.alarm ->setCurrentFragment(reminder_fragment)
                    R.id.tasks ->setCurrentFragment(rules_fragment)

                }
                true
            }






    }

    // for android version 8.0 and above for alarms and notification you need to specify the channel id
    // this function is built for that purpose only
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name:CharSequence="75HARD"
            val description="Channel for notifications"
            val importance=NotificationManager.IMPORTANCE_HIGH
            val channel=NotificationChannel("75Hard",name,importance)
            channel.description=description
            val notificationManager= getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)

        }
    }
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_icon-> Toast.makeText(this,"YOUR PROFILE", Toast.LENGTH_SHORT).show()
            R.id.user_settings-> Toast.makeText(this,"user settings", Toast.LENGTH_SHORT).show()

        }
        return true
    }*/
    // we create a function to replace fragments
    // we will add new fragments into our fragment container i.e--> fragment_container
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment) //here fragment is our parameter defining which fragment will go inside our container
            commit()
        }
}