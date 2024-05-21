package com.example.convidados.data

import GuestDataBase
import android.content.Context
import com.example.convidados.models.GuestModel

class GuestRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDatabase(context).guestDAO()

    fun postGuest(guest: GuestModel): Boolean {
        return guestDataBase.insert(guest) > 0
    }

    fun updateGuest(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
    }

    fun getGuestById(guestId: Int): GuestModel {
        return guestDataBase.getGuestById(guestId)
    }

    fun deleteGuest(guest: GuestModel) {
        return guestDataBase.delete(guest)
    }

    fun getAllGuests(): List<GuestModel> {
        return guestDataBase.getAllGuests()
    }

    fun getPresents(): List<GuestModel> {
        return guestDataBase.getPresents()
    }

    fun getAbsents(): List<GuestModel> {
        return guestDataBase.getAbsents()
    }


}