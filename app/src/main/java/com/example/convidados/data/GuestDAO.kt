package com.example.convidados.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.convidados.models.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun getGuestById(id: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getAllGuests(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresents(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsents(): List<GuestModel>

}