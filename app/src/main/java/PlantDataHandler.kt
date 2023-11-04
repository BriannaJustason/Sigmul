package com.delasign.samplestarterproject.utils.data
import PlantResponse
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.sigmul3.R
import com.google.gson.Gson

class PlantDataHandler(private val context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun fetchPlantData(
        plantNameSearch: String,
        edibleChoice: String,
        poisonousChoice: String,
        waterChoice: String,
        sunlightChoice: String,
        onResponse: (PlantResponse?) -> Unit,
        onError: (String) -> Unit
    ) {
        val key = context.getString(R.string.key)
        val url = "https://perenual.com/api/species-list?key=$key" +
                "q=$plantNameSearch&" +
                "edible=$edibleChoice&" +
                "poisonous=$poisonousChoice&" +
                "watering=$waterChoice&" +
                "sunlight=$sunlightChoice"

        Log.d("VolleyRequest", "URL is: $url")
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val plantResponse = Gson().fromJson(response.toString(), PlantResponse::class.java)
                onResponse(plantResponse)
            },
            { error ->
                onError(error.message ?: "An error occurred.")
            }
        )

        requestQueue.add(request)
    }
}
