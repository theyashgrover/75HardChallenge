package com.example.a75hardchallenge.RoomDB.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a75hardchallenge.RoomDB.DAO.UserPrefferedUIDAO
import com.example.a75hardchallenge.RoomDB.Entities.UserPrefferedUITxt


@Database(entities = [UserPrefferedUITxt::class], version = 5, exportSchema = false)
abstract class UserPreferredUIDatabase : RoomDatabase() {
    abstract fun getUserPrefferedUIDao(): UserPrefferedUIDAO

   companion object {
       val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWorkoutOutProgressTxt' TEXT NOT NULL DEFAULT '0'")

            }

        }
        val migration_2_3= object :Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWorkoutInProgressTxt' TEXT NOT NULL DEFAULT '0'")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiBookPageProgressTxt' TEXT NOT NULL DEFAULT '0'")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiMealProgressTxt' TEXT NOT NULL DEFAULT '0'")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiAlcoholProgressTxt' TEXT NOT NULL DEFAULT '0'")
            }

        }
        private val migration_3_4=object :Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWaterProgressIncomplete' INT NOT NULL DEFAULT (0)")
            }

        }
        private val migration_4_5=object :Migration(4,5){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWaterProgressComplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWaterProgress75' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWaterProgressHalf' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWaterProgress25' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWorkoutOutProgressIncomplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWorkoutOutProgressComplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWorkoutInProgressIncomplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiWorkoutInProgressComplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiBookPageProgressIncomplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiBookPageProgressComplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiBookPageProgress75' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiBookPageProgressHalf' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiBookPageProgress25' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiMealProgressIncomplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiMealProgressComplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiAlcoholProgressIncomplete' INT NOT NULL DEFAULT (0)")
                database.execSQL("ALTER TABLE 'selectedUI' ADD COLUMN 'uiAlcoholProgressComplete' INT NOT NULL DEFAULT (0)")

            }

        }
        private val migration_5_6=object :Migration(5,6){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWaterProgressIncomplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWaterProgressComplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWaterProgress75'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWaterProgressHalf'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWaterProgress25'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWorkoutOutProgressIncomplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWorkoutOutProgressComplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWorkoutInProgressIncomplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiWorkoutInProgressComplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiBookPageProgressIncomplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiBookPageProgressComplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiBookPageProgress75'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiBookPageProgressHalf'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiBookPageProgress25'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiMealProgressIncomplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiMealProgressComplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiAlcoholProgressIncomplete'")
                database.execSQL("ALTER TABLE 'selectedUI' DROP COLUMN 'uiAlcoholProgressComplete'")
            }


        }

        @Volatile
        private var dbINSTANCE: UserPreferredUIDatabase? = null
        fun getDatabase(context: Context): UserPreferredUIDatabase {
            //because ek time pe 2 threads access kr sakte and we will have two
            //instances so that's why we use synchronised lock
           if (dbINSTANCE == null) {
                synchronized(this) {
                    dbINSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserPreferredUIDatabase::class.java,
                        "UserPreferredUIDatabase"
                    )
                        .addMigrations(migration_4_5)
                        .build()
                }

            }
           return dbINSTANCE!!
        }
    }

}

