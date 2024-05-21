package com.example.convidados.view.viewholder

import android.content.DialogInterface
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.models.GuestModel
import com.example.convidados.view.GuestFormActivity
import com.example.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        val present = if (guest.presence) "Presente" else "Ausente"
        bind.textName.text = guest.name
        bind.textStatus.text = "Status: $present"

        bind.linearLayoutConvidado.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.linearLayoutConvidado.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remover convidado")
                .setMessage("Deseja remover o convidado ${guest.name}?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onDelete(guest)
                }
                .setNegativeButton("Não", null).create().show()

            true
        }
    }
}