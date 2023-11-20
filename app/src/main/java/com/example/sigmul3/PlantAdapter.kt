package com.delasign.samplestarterproject.utils.data.com.example.sigmul3

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
import Plant
import android.content.ContentValues

class PlantAdapter(private val plantList: List<Plant>, private val shouldHideAddButton: Boolean):
    RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.plantTitleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        val addButton: Button = itemView.findViewById(R.id.addButton)
    }
    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(plantList[position])
                }
            }
        }
    }
    private var onItemClickListener: ((Plant) -> Unit)? = null

    fun setOnItemClickListener(listener: (Plant) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plantList[position]
        holder.titleTextView.text = plant.commonName

        if (shouldHideAddButton) {
            // Hide the "add" button
            holder.addButton.visibility = View.GONE
        } else {
            // Show the "add" button
            holder.addButton.visibility = View.VISIBLE
        }
        if (plant.defaultImage != null) {
            // Load the image using the specified URL
            Picasso.get().load(plant.defaultImage.originalUrl).into(holder.imageView)
        } else {
            // Load a default image if the URL is null or not specified
            Picasso.get().load(R.drawable.sigmul1_background).into(holder.imageView)
        }

        holder.addButton.setOnClickListener {
            val plant = plantList[position]

            // Initialize the SQLite database helper
            val dbHelper = PlantDBHelper(holder.itemView.context)

            // Open the database for writing
            val db = dbHelper.writableDatabase

            // Create a ContentValues object to store the plant data
            val values = ContentValues()
            values.put(PlantDBHelper.COLUMN_COMMON_NAME, plant.commonName)
            values.put(PlantDBHelper.COLUMN_IMAGE_URL, plant.defaultImage?.originalUrl ?: "")
            values.put(PlantDBHelper.COLUMN_EDIBLE, plant.edible?.toString() ?: "")
            values.put(PlantDBHelper.COLUMN_POISONOUS, plant.poisonous?.toString() ?: "")
            values.put(PlantDBHelper.COLUMN_WATER, plant.watering?.toString() ?: "")
            values.put(PlantDBHelper.COLUMN_SUNLIGHT, plant.sunlight?.toString() ?: "")

            // Insert the plant data into the database
            val newRowId = db.insert(PlantDBHelper.PLANT_TABLE_NAME, null, values)

            // Close the database
            db.close()

            if (newRowId != -1L) {
                // Insertion was successful
                Toast.makeText(holder.itemView.context, "Plant added to database", Toast.LENGTH_SHORT).show()
            } else {
                // Insertion failed
                Toast.makeText(holder.itemView.context, "Failed to add the plant to the database", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return plantList.size
    }
}
