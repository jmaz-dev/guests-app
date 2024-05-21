package com.example.convidados.view.listener

import com.example.convidados.models.GuestModel

interface OnGuestListener {
    fun onClick(id: Int)
    fun onDelete(guest: GuestModel)
}