package com.example.projectgithub.data.local

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.projectgithub.data.model.ResponseUserGithub



@Database(entities =  [ResponseUserGithub.Item::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}