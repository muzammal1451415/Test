package com.app.test.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.test.R
import com.app.test.databinding.FragmentHomeBinding
import com.app.test.domain.home.entity.Results
import com.app.test.presentation.home.adapter.NearbyChargingStationListAdapter
import com.app.test.utils.showToast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import java.lang.NumberFormatException
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    val TAG = "HomeFragment"

    @Inject
    lateinit var viewModel:HomeViewModel

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        inits()
        initObservers()
        initClickListener()

        showNearByChargingStations(500)

        return binding.root
    }


    private fun initClickListener(){
        binding.btnGetResult.setOnClickListener {
            try {
                val radius = binding.etRadius.text.toString().toInt()
                showNearByChargingStations(radius)

            }catch (e:NumberFormatException){
                requireContext().showToast(getString(R.string.invalid_input))
            }catch (e1: Exception){
                requireContext().showToast(getString(R.string.something_went_wrong))
            }
        }
    }

    private fun inits(){
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        viewModel.mState.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                handleState(state)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: HomeStateModel){
        when(state){
            is HomeStateModel.StatusFailed  -> {
                // handle Status Failed State UI
                requireContext().showToast(state.message)
                binding.progressbar.visibility = View.GONE
                binding.recyclerview.visibility  = View.GONE
            }
            is HomeStateModel.IsLoading -> {
                // handle Loading State UI
                binding.progressbar.visibility = View.VISIBLE
                binding.recyclerview.visibility  = View.GONE
            }

            is HomeStateModel.GenericError -> {
                // handle Generic Error State UI
                requireContext().showToast(state.messagge)
                binding.progressbar.visibility = View.GONE
                binding.recyclerview.visibility  = View.GONE
            }

            is HomeStateModel.NearByChargingPlacesSuccess -> {
                // handle NearByChargingPlacesSuccess Success State UI
                binding.progressbar.visibility = View.GONE
                binding.recyclerview.visibility  = View.VISIBLE
                handleHomeVideosResponse(state.videosResponse)
            }
            else -> {}
        }
    }

    private fun handleHomeVideosResponse(response: List<Results>) {
        val adapter = NearbyChargingStationListAdapter(response)
        binding.recyclerview.adapter = adapter
        adapter.onTapListener = object: NearbyChargingStationListAdapter.OnItemTap{
            override fun onTap(position: Int, result: Results) {
                var bundle = Bundle().also {
                    it.putString("name",result.name)
                    it.putString("location","${result.geometry?.location?.lat},${result.geometry?.location?.lng}")
                    it.putString("business_status",result.businessStatus)
                    it.putString("rating",result.rating.toString())
                    it.putString("user_rating",result.userRatingsTotal.toString())
                    it.putString("place_id",result.placeId)
                }
                findNavController().navigate(R.id.action_homeFragment_to_destinationFragment,bundle)
            }

        }
    }



    @SuppressLint("MissingPermission")
    private fun showNearByChargingStations(radius: Int){
        if(isLocationPermissionGranted()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if(location == null){
                        requireContext().showToast(getString(R.string.unable_to_get_current_location))
                        return@addOnSuccessListener
                    }
                    val formatedLocation = location.latitude.toString()+","+location.longitude.toString()
                    viewModel.getNearByChargingStations(formatedLocation,radius)

                }.addOnFailureListener{
                    requireContext().showToast(getString(R.string.error_in_getting_current_location))
                }
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
            false
        } else {
            true
        }
    }


}