package com.bootcamp_android.parking_app.view.fragments

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.domain.model.Lot
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.parking_app.R
import com.bootcamp_android.parking_app.databinding.FragmentReservationsBinding
import com.bootcamp_android.parking_app.viewmodel.LotsViewModel
import com.bootcamp_android.parking_app.viewmodel.ReservationsViewModel
import com.bootcamp_android.parking_app.viewmodel.ViewModelFactory
import com.bootcamp_android.parking_app.viewmodel.adapters.ReservationsAdapter

class ReservationListFragment : Fragment() {

    private lateinit var reservationsViewModel: ReservationsViewModel
    private lateinit var lotsViewModel: LotsViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var binding: FragmentReservationsBinding? = null
    private val args: ReservationListFragmentArgs by navArgs()
    private var lot: Lot? = null

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ViewModelFactory(requireContext())
        lotsViewModel = ViewModelProvider(
            this,viewModelFactory
        ).get(LotsViewModel::class.java)
        reservationsViewModel = ViewModelProvider(
            this,viewModelFactory
        ).get(ReservationsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_reservations,container,false)
    }

    override fun onViewCreated(itemView: View,savedInstanceState: Bundle?) {
        binding = FragmentReservationsBinding.bind(itemView)
        lot = args.lot
        var reservations = args.lot.reservations
        lotsViewModel.lots.observe(viewLifecycleOwner) { lots ->
            lot = lots.find { l ->
                l.parkingLot == lot?.parkingLot
            }
            reservations = args.lot.reservations
        }

        binding?.apply {
            recyclerReservationList.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ReservationsAdapter(reservations) { reservation,pos ->
                    onDeleteClick(
                        reservation,this,reservations,pos
                    )
                }
            }
            fab.setOnClickListener {
                val action =ReservationListFragmentDirections.fabResToAdd()
                findNavController().navigate(action)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        lotsViewModel.loadLots()
    }

    private fun onDeleteClick(
        reservation: Reservation,recyclerView: RecyclerView,reservations: MutableList<Reservation>,pos: Int
    ) {
        val builder = AlertDialog.Builder(requireContext())
        Toast.makeText(activity,"The reservation code ${reservation.authorizationCode} ",Toast.LENGTH_SHORT).show()

        builder.setTitle("Delete Reservation ${reservation.id}")
        val input =
            EditText(requireContext()) // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.inputType = InputType.TYPE_CLASS_NUMBER //or InputType.TYPE_NUMBER_VARIATION_PASSWORD
        input.clipToOutline
        builder.setView(input)
            .setMessage("Are you sure you want to delete this reservation? Please input the authorization code to confirm")
            .setCancelable(true) // dialog box in cancellable
            // set positive button
            //take two parameters dialogInterface and an int
            .setPositiveButton("OK") { dialogInterface,_ ->
                reservationsViewModel.deleteReservation(
                    reservation,input.text.toString()
                )
                reservationsViewModel.mutableSuccessfulDelete.observe(viewLifecycleOwner) {
                    if(it) {
                        Log.d(TAG,"onDeleteClick: pos: $pos")
                        reservations.removeAt(pos)
                        recyclerView.adapter?.notifyItemRemoved(pos)
                        Toast.makeText(
                            activity,"The reservation code ${reservation.id} has been deleted",Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(activity,"The Reservation has not been deleted",Toast.LENGTH_SHORT).show()
                    }
                }

                dialogInterface.dismiss()
            }.setNegativeButton("CANCEL") { dialogInterface,_ -> // cancel the dialogbox
                dialogInterface.cancel()
            }.show()
    }
}
