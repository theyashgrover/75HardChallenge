package com.example.a75hardchallenge.RoomDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selectedUI")
data class UserPrefferedUITxt(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val uiWaterProgressTxt: String="0",
    val uiWorkoutOutProgressTxt: String="0",
    val uiWorkoutInProgressTxt:String="0",
    val uiBookPageProgressTxt:String="0",
    val uiMealProgressTxt:String="0",
    val uiAlcoholProgressTxt:String="0",
    val uiWaterProgressIncomplete: Int=0,
    //new
    val uiWaterProgressComplete:Int=0,
    val uiWaterProgress75:Int=0,
    val uiWaterProgressHalf:Int=0,
    val uiWaterProgress25:Int=0,
    val uiWorkoutOutProgressIncomplete: Int=0,
    val uiWorkoutOutProgressComplete: Int=0,
    val uiWorkoutInProgressIncomplete:Int=0,
    val uiWorkoutInProgressComplete:Int=0,
    val uiBookPageProgressIncomplete:Int=0,
    val uiBookPageProgressComplete:Int=0,
    val uiBookPageProgress75:Int=0,
    val uiBookPageProgressHalf:Int=0,
    val uiBookPageProgress25:Int=0,
    val uiMealProgressIncomplete:Int=0,
    val uiMealProgressComplete:Int=0,
    val uiAlcoholProgressIncomplete:Int=0,
    val uiAlcoholProgressComplete:Int=0,

    )
