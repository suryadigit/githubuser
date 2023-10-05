package com.example.projectgithub.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectgithub.data.model.ResponseUserGithub


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ResponseUserGithub.Item)

    @Query("SELECT * FROM User")
    fun loadAll(): LiveData<MutableList<ResponseUserGithub.Item>>

    @Query("SELECT * FROM User WHERE id = :id LIMIT 1")
    fun findById(id: Int): ResponseUserGithub.Item

    @Delete
    fun delete(user: ResponseUserGithub.Item)
 }
