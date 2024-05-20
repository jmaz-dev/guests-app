package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: AllGuestsViewModel

    private val adapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel =
            ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        /*Layout(RecyclerView id)*/
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        /*Adapter(Layout that fill the RecyclerView)*/
        binding.recyclerAllGuests.adapter = adapter

        /*Get Listener*/
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAllGuests()

            }

        }

        /*Attach Listener*/
        adapter.attachListener(listener)

        /*Get all guests*/
        viewModel.getAllGuests()

        /*ViewModel observer*/
        observer()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}