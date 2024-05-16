package com.example.convidados.repository

class GuestRepository {

    /*Singleton*/

    companion object {
        private lateinit var reporsitory: GuestRepository

        fun getInstance(): GuestRepository {
            if (!Companion::reporsitory.isInitialized) {
                reporsitory = GuestRepository()
            }
            return reporsitory
        }
    }
}