package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.models.GuestModel
import com.example.convidados.data.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAllGuests() {
        listAllGuests.value = repository.getAllGuests()
    }

    fun getPresents() {
        listAllGuests.value = repository.getPresents()
    }

    fun getAbsents() {
        listAllGuests.value = repository.getAbsents()
    }

    fun delete(guestId: Int) {
        val guest = repository.getGuestById(guestId)
        repository.deleteGuest(guest)
    }
}