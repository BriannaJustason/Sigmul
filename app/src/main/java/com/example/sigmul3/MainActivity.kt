package com.example.sigmul3

import PlantResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import android.util.Log
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment())

        val gson = Gson()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.garden -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.add -> {
                    loadFragment(AddFragment())
                    true
                }

                else -> {
                    false}
            }
        }

        val url = "https://perenual.com/api/species-list?key=sk-2OK86503ba7f18f8d2166&q=Indian plantain"
        val requestUrl = "https://perenual.com/api/species-list?key=sk-2OK86503ba7f18f8d2166&&q=&edible=&poisonous=&watering=&sunlight="

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { jsonResponse ->
                val response = gson.fromJson(jsonResponse, PlantResponse::class.java)

                val imageURL = response.data.firstOrNull()?.defaultImage?.regularUrl
                Log.d("POOP", "URL is: $imageURL")

                val imageView = findViewById<ImageView>(R.id.imageView2)
                Picasso.get().load(imageURL).into(imageView)
            },
            { Log.d("POOP", "That didn't work!") })

        queue.add(stringRequest)
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}