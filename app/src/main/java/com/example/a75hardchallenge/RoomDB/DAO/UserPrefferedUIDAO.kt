package com.example.a75hardchallenge.RoomDB.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a75hardchallenge.RoomDB.Entities.UserPrefferedUITxt


@Dao
interface  UserPrefferedUIDAO {
    @Insert
    suspend fun insertUIwtrText(selectedUI: UserPrefferedUITxt)
    @Update
    suspend fun updateUIwtrText(selectedUI: UserPrefferedUITxt)
    @Query("SELECT *FROM selectedUI")
     fun getUIwtrText():LiveData<List<UserPrefferedUITxt>>


}