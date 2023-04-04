package com.skyline_info_system.baseapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skyline_info_system.baseapp.db.dao.UserDao
import com.skyline_info_system.baseapp.db.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}