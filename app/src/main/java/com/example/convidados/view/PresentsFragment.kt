package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentPresentsBinding
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewmodel.GuestsViewModel

class PresentsFragment : Fragment() {

    private var _binding: FragmentPresentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel

    private val adapter = GuestAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]

        _binding = FragmentPresentsBinding.inflate(inflater, container, false)

        /*Layout*/
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        /*Adapter*/
        binding.recyclerGuests.adapter = adapter

        /*Get Listener*/
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresents()

            }

        }

        /*Attach Listener*/
        adapter.attachListener(listener)

        /*Observer*/
        observer()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPresents()

    }

    /*Place list of guests*/
    private fun observer() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}