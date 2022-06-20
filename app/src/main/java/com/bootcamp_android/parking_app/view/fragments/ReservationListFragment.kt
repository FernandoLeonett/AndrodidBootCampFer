package com.bootcamp_android.parking_app.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
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
import com.bootcamp_android.domain.model.Reservation
import com.bootcamp_android.domain.util.DeleteReservationRequest
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
        lotsViewModel.loadLots()


        lotsViewModel.lots.observe(viewLifecycleOwner) { lots ->
            initRecycleReservations(lots.single {
                it.parkingLot == args.lotId.toInt()
            }.reservations)
        }
        binding?.apply {
            fab.setOnClickListener {
                val action = ReservationListFragmentDirections.fabResToAdd()
                findNavController().navigate(action)
            }
        }
    }

    private fun initRecycleReservations(reservations: MutableList<Reservation>) {
        binding?.apply {
            recyclerReservationList.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ReservationsAdapter(reservations) { reservation,pos ->
                    onDeleteClick(
                        reservation,this,reservations,pos
                    )
                }
            }
        }
    }

    private fun onDeleteClick(
        reservation: Reservation,recyclerView: RecyclerView,reservations: MutableList<Reservation>,pos: Int
    ) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle(getString(R.string.title_delete_dialog_reservation))
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_NUMBER

        builder.setView(input).setMessage(getString(R.string.dialog_message_delete_reservation))
            .setCancelable(true)
            .setPositiveButton(getString(R.string.text_btn_delete_positive)) { dialogInterface,_ ->
                reservationsViewModel.deleteReservation(
                    reservation,input.text.toString()
                )
                reservationsViewModel.successfullyDeleted.observe(viewLifecycleOwner) {
                    var msg = getString(R.string.msg_reservation_delete_error)
                    if(it == DeleteReservationRequest.SUCCESS) {
                        if(pos <= reservations.size && reservations.isNotEmpty()) {
                            reservations.removeAt(pos)
                            recyclerView.adapter?.notifyItemRemoved(pos) //
                            msg = getString(R.string.msg_reservation_delete_success)
                        }
                    } else if(it == DeleteReservationRequest.BAD_AUTHORIZATION_CODE) {
                        msg = getString(R.string.msg_reservation_delete_error_authorization_code)
                    }
                    Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
                }
                dialogInterface.dismiss()
            }.setNegativeButton(getString(R.string.text_btn_delete_negative)) { dialogInterface,_ ->
                dialogInterface.cancel()
            }.show()
    }

    override fun onResume() {
        super.onResume()
        lotsViewModel.loadLots()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
