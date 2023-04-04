package com.skyline_info_system.baseapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userId: Int,
    val userName: String,
    val userPassword: String
)
