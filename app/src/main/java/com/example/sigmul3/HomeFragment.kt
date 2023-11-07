package com.example.sigmul3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delasign.samplestarterproject.utils.data.com.example.sigmul3.PlantAdapter
import com.delasign.samplestarterproject.utils.data.com.example.sigmul3.PlantDBHelper

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.garden, container, false)

        // Initialize RecyclerView
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.plantRecyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        val plantDBHelper = PlantDBHelper(requireContext())
        val plantList = plantDBHelper.getAllPlants()
        val plantAdapter = PlantAdapter(plantList, true)
        recyclerView.adapter = plantAdapter

        return rootView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Move any UI-related code to onCreateView
    }

}