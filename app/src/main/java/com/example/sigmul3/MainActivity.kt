package com.example.sigmul3

import PlantResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import com.google.gson.Gson
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val idtextView = findViewById<TextView>(R.id.textView)
        val gson = Gson()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://perenual.com/api/species-list?key=sk-2OK86503ba7f18f8d2166&q=Indian plantain"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { jsonResponse ->
                val response = gson.fromJson(jsonResponse, PlantResponse::class.java)

                val imageURL = response.data.firstOrNull()?.defaultImage?.regularUrl
                Log.d("POOP", "URL is: ${imageURL}")

                val imageView = findViewById<ImageView>(R.id.imageView2)
                Picasso.get().load(imageURL).into(imageView)
            },
            { idtextView.text = "That didn't work!" })

        queue.add(stringRequest)


    }
}