package com.example.aipxperts.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
public interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers():List<User>
}