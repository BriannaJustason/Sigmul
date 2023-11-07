package com.example.sigmul3


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delasign.samplestarterproject.utils.data.PlantDataHandler
import com.delasign.samplestarterproject.utils.data.com.example.sigmul3.PlantAdapter


class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Move any UI-related code to onCreateView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantName = view.findViewById<EditText>(R.id.plantSearchBox)
        val edibleSpinner = view.findViewById<Spinner>(R.id.edibleSpinner)
        val poisonousSpinner = view.findViewById<Spinner>(R.id.poisonousSpinner)
        val waterSpinner = view.findViewById<Spinner>(R.id.wateringSpinner)
        val sunlightSpinner = view.findViewById<Spinner>(R.id.sunlightSpinner)

        var plantNameSearch = ""
        var edibleChoice = ""
        var poisonousChoice = ""
        var waterChoice = ""
        var sunlightChoice = ""

        plantName.addTextChangedListener {
            plantNameSearch = plantName.text.toString()
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.EdibleChoice,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Set the layout for the dropdown menu
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set the adapter for the spinner
            edibleSpinner.adapter = adapter
            // Set a listener to perform actions based on what is selected
            edibleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Perform actions based on the selected item
                    when (parent?.getItemAtPosition(position).toString()) {
                        "Yes" -> {
                            edibleChoice = "yes"
                        }

                        "No" -> {
                            edibleChoice = "yes"
                        }

                        "N/A" -> {
                            edibleChoice = ""
                        }

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.PoisonousChoice,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Set the layout for the dropdown menu
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set the adapter for the spinner
            poisonousSpinner.adapter = adapter
            // Set a listener to perform actions based on what is selected
            poisonousSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Perform actions based on the selected item
                    when (parent?.getItemAtPosition(position).toString()) {
                        "Yes" -> {
                            poisonousChoice = "yes"
                        }

                        "No" -> {
                            poisonousChoice = "yes"
                        }
                        "N/A" -> {
                            edibleChoice = ""
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.WateringChoice,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Set the layout for the dropdown menu
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set the adapter for the spinner
            waterSpinner.adapter = adapter
            // Set a listener to perform actions based on what is selected
            waterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Perform actions based on the selected item
                    when (parent?.getItemAtPosition(position).toString()) {
                        "Frequent" -> {
                            waterChoice = "Frequent"
                        }

                        "Average" -> {
                            waterChoice = "Average"
                        }

                        "Minimum" -> {
                            waterChoice = "Minimum"
                        }

                        "None" -> {
                            waterChoice = "None"
                        }
                        "N/A" -> {
                            edibleChoice = ""
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.SunlightChoice,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Set the layout for the dropdown menu
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set the adapter for the spinner
            sunlightSpinner.adapter = adapter
            // Set a listener to perform actions based on what is selected
            sunlightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Perform actions based on the selected item
                    when (parent?.getItemAtPosition(position).toString()) {
                        "Full Shade" -> {
                            sunlightChoice = "full_shade"
                        }

                        "Part Shade" -> {
                            sunlightChoice = "part_shade"
                        }

                        "Sun-Part Shade" -> {
                            sunlightChoice = "sun-part_shade"
                        }

                        "Full Sun" -> {
                            sunlightChoice = "full_sun"
                        }
                        "N/A" -> {
                            edibleChoice = ""
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        view.findViewById<View>(R.id.searchButton).setOnClickListener {
            println("Button Clicked")
            // Make a URL that is similar to this one: val requestUrl = "https://perenual.com/api/species-list?key=sk-2OK86503ba7f18f8d2166&&q=&edible=&poisonous=&watering=&sunlight="
            // Extract the key to a resource
            val key = getString(R.string.key)
            val requestUrl = "https://perenual.com/api/species-list?key=$key" +
                    "q=$plantNameSearch&" +
                    "edible=$edibleChoice&" +
                    "poisonous=$poisonousChoice&" +
                    "watering=$waterChoice&" +
                    "sunlight=$sunlightChoice"

            val plantDataHandler = PlantDataHandler(requireContext())
            plantDataHandler.fetchPlantData(
                plantNameSearch, edibleChoice, poisonousChoice, waterChoice, sunlightChoice,
                onResponse = { plantResponse ->

                    if (plantResponse != null) {
                        val plantList = plantResponse.data
                        println(plantList)
                        // RecyclerView setup
                        val recyclerView = view.findViewById<RecyclerView>(R.id.plantRecyclerView)
                        val layoutManager = LinearLayoutManager(context)
                        recyclerView.layoutManager = layoutManager

                        // Create an instance of PlantAdapter and set it to the RecyclerView
                        val plantAdapter = PlantAdapter(plantList) // Provide your list of plants here
                        recyclerView.adapter = plantAdapter

                    }
                },
                onError = { errorMessage ->
                    // Handle errors here, e.g., show an error message to the user.
                    Log.e("VolleyRequest", errorMessage)
                }
            )
        }


    }
}



