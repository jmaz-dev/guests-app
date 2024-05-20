package com.example.convidados.constants

class MessageStrings private constructor() {

    object ERROR_MESSAGES {
        const val GENERIC = "Something went wrong"
    }

    object SUCCESS_MESSAGES {
        const val CREATE = "Convidado criado com sucesso"
        const val UPDATE = "Convidado atualizado com sucesso"
        const val DELETE = "Convidado deletado com sucesso"
    }
}