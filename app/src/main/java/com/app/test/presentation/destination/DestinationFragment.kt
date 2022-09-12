package com.app.test.presentation.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.databinding.FragmentDestinationBinding

class DestinationFragment : Fragment() {
    lateinit var binding: FragmentDestinationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDestinationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val location = arguments?.getString("location")
        val businessStatus = arguments?.getString("business_status")
        val rating = arguments?.getString("rating")
        val userRating = arguments?.getString("user_rating")
        val placeId = arguments?.getString("place_id")

        binding.name.text = name
        binding.location.text = location
        binding.businessStatus.text = businessStatus
        binding.rating.text = rating
        binding.userRating.text = userRating
        binding.placeId.text = placeId


    }

}