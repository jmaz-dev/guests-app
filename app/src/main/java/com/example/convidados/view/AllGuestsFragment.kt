package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel =
            ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        /*Layout*/
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        /*Adapter*/
        binding.recyclerAllGuests.adapter

        observer()

        viewModel.getAllGuests()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        viewModel.guests.observe(viewLifecycleOwner) {
            val s = ""
        }
    }
}