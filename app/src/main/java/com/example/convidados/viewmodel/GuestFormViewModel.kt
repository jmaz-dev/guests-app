package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.models.GuestModel
import com.example.convidados.data.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)

    private val listGuestById = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = listGuestById

    private val isSave = MutableLiveData<Boolean>()
    val save: LiveData<Boolean> = isSave

    fun postGuest(guest: GuestModel) {
        if (guest.id == 0) {
            isSave.value = repository.postGuest(guest)
        } else {
            isSave.value = repository.updateGuest(guest)
        }
    }

    fun getGuestById(guestId: Int) {
        listGuestById.value = repository.getGuestById(guestId)
    }
}