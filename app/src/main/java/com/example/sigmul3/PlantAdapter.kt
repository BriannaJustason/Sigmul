package com.delasign.samplestarterproject.utils.data.com.example.sigmul3

import Plant
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sigmul3.R
import com.squareup.picasso.Picasso


class PlantAdapter(private val plantList: List<Plant>) :
    RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.plantTitleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        val addButton: Button = itemView.findViewById(R.id.addButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plantList[position]
        holder.titleTextView.text = plant.commonName
        
        if (plant.defaultImage != null) {
            // Load the image using the specified URL
            Picasso.get().load(plant.defaultImage.originalUrl).into(holder.imageView)
        } else {
            // Load a default image if the URL is null or not specified
            Picasso.get().load(R.drawable.sigmul1_background).into(holder.imageView)
        }

        holder.addButton.setOnClickListener {
            // Perform an action when the "add" button is clicked
            // You can access the plant associated with this item using plantList[position]
            val clickedPlant = plantList[position]

            // Now, insert the clicked plant into the database
            val dbHelper = PlantDBHelper(holder.itemView.context)
            val result = dbHelper.insertPlant(clickedPlant)

            if (result != -1L) {
                // Plant was successfully added to the database
                val message = "Added ${clickedPlant.commonName} to your collection"
                Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
            } else {
                // Error occurred while adding the plant to the database
                Toast.makeText(holder.itemView.context, "Failed to add the plant", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return plantList.size
    }
}
