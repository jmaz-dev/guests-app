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
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.models.GuestModel
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel

    private val adapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel =
            ViewModelProvider(this)[GuestsViewModel::class.java]

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        /*Layout(RecyclerView id)*/
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        /*Adapter(Layout that fill the RecyclerView)*/
        binding.recyclerAllGuests.adapter = adapter

        /*Get Listener*/
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(guest: GuestModel) {
                viewModel.delete(guest)
                viewModel.getAllGuests()

            }

        }

        /*Attach Listener*/
        adapter.attachListener(listener)

        /*ViewModel observer*/
        observer()

        return binding.root
    }

    /*Get all guests*/
    override fun onResume() {
        super.onResume()
        viewModel.getAllGuests()

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