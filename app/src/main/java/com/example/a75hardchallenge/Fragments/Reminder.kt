package com.example.a75hardchallenge.Fragments

//import com.example.a75hardchallenge.Broadcast.AlarmReceiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a75hardchallenge.R
import com.example.a75hardchallenge.databinding.FragmentReminderBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

class Reminder : Fragment(R.layout.fragment_reminder) {
    private lateinit var picker: MaterialTimePicker
    private lateinit var Switch: SwitchMaterial
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private var _binding: FragmentReminderBinding? = null
    val calendar = Calendar.getInstance()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        val view = binding.root
        createNotificationChannel()
        binding.reminderMealTime.setOnClickListener {

            showTimePicker(R.id.reminder_meal_time)
            alarmActivate()


        }
        binding.reminderGymTime.setOnClickListener {
            showTimePicker(R.id.reminder_gym_time)
            alarmActivate()
        }
        binding.reminderWaterTime.setOnClickListener {
            showTimePicker(R.id.reminder_water_time)
            alarmActivate()
        }
        binding.addReminder.setOnClickListener {
            showTimePicker(R.id.reminder_customtime)
            binding.reminderCustomtime.visibility = View.VISIBLE
            binding.customTitle.visibility = View.VISIBLE
            binding.customTitle.text = "Your Custom Reminder"
            binding.customImage.visibility = View.VISIBLE
            alarmActivate()
        }


        return view
    }


    private fun createNotificationChannel() {
       if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
           val name="75HardChallengeApp"
           val description="Achieve your goals"
           val importance=NotificationManager.IMPORTANCE_HIGH
           val channel=NotificationChannel("75Hard",name,importance)
           channel.description=description
           val notificationManager= context?.getSystemService(
               NotificationManager::class.java
           )
           notificationManager?.createNotificationChannel(channel)
       }
    }

    private fun alarmActivate() {
        Switch = binding.reminderSwitch
        if (Switch.isActivated){

        alarmManager =
            requireContext().applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this.context, NotificationManager::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            this.context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Toast.makeText(this.context,"Alarm Set Succesfully",Toast.LENGTH_SHORT).show()
    }}

    private fun showTimePicker(id: Int) {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Choose your time")
            .build()

        picker.addOnPositiveButtonClickListener {
            if (id == R.id.reminder_meal_time) {
                //if (isNotificationPermissionGranted()){
                    if (picker.hour > 12) {
                        binding.reminderMealTime.text =
                            String.format("%02d", picker.hour - 12) + ":" + String.format(
                                "%02d",
                                picker.minute
                            ) + " PM"
                    } else {
                        binding.reminderMealTime.text =
                            String.format("%02d", picker.hour) + ":" + String.format("%02d", picker.minute) + " AM"
                    }
                } else if (id == R.id.reminder_gym_time) {
                    if (picker.hour > 12) {
                        binding.reminderGymTime.text =
                            String.format("%02d", picker.hour - 12) + ":" + String.format(
                                "%02d",
                                picker.minute
                            ) + " PM"
                    } else {
                        binding.reminderGymTime.text =
                            String.format("%02d", picker.hour) + ":" + String.format("%02d", picker.minute) + " AM"
                    }
                } else if (id == R.id.reminder_water_time) {
                    if (picker.hour > 12) {
                        binding.reminderWaterTime.text =
                            String.format("%02d", picker.hour - 12) + ":" + String.format(
                                "%02d",
                                picker.minute
                            ) + " PM"
                    } else {
                        binding.reminderWaterTime.text =
                            String.format("%02d", picker.hour) + ":" + String.format("%02d", picker.minute) + " AM"
                    }
                } else {
                    if (picker.hour > 12) {
                        binding.reminderCustomtime.text =
                            String.format("%02d", picker.hour - 12) + ":" + String.format(
                                "%02d",
                                picker.minute
                            ) + " PM"
                    } else {
                        binding.reminderCustomtime.text =
                            String.format("%02d", picker.hour) + ":" + String.format("%02d", picker.minute) + " AM"
                    }
                }

                calendar[Calendar.HOUR_OF_DAY] = picker.hour
                calendar[Calendar.MINUTE] = picker.minute
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MILLISECOND] = 0
            //alarmActivate()
            }

            picker.show(childFragmentManager, "75Hard")

                }               // }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
