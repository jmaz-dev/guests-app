package com.example.convidados.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.models.GuestModel
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding

    private lateinit var viewModel: GuestFormViewModel

    private var guestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        /*setDefault*/
        binding.radioPresent.isChecked = true

        /*LoadData & Observe*/
        loadData()

        observer()

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        if (v.id == R.id.button_save) {
            val name: String = binding.editName.text.toString()

            val presence: Boolean = binding.radioPresent.isChecked

            viewModel.postGuest(GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            })
        }

    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)

            viewModel.getGuestById(guestId)
        }
    }

    private fun observer() {
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
            binding.buttonSave.text = "Alterar"
        })

        viewModel.save.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
        })


    }


}