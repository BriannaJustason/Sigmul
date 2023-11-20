package com.delasign.samplestarterproject.utils.data.com.example.sigmul3

import Plant
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.example.sigmul3.R

class PlantPopupWindow(private val context: Context) {

    private val popupView: View = LayoutInflater.from(context).inflate(R.layout.plant_dialog, null)
    private val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

    fun showPlantPopup(plant: Plant) {
        // Populate the popup views with plant data
        val plantNameTextView = popupView.findViewById<TextView>(R.id.name)
        val plantDescriptionTextView = popupView.findViewById<TextView>(R.id.sunlight)

        val nameText: CharSequence = plant.commonName.toString()
        val descriptionText: CharSequence = plant.sunlight.toString()

        plantNameTextView.text = nameText
        plantDescriptionTextView.text = descriptionText

        // Show the popup
        popupWindow.showAtLocation(popupView, 0, 0, 0)
    }

    fun dismissPlantPopup() {
        // Dismiss the popup
        popupWindow.dismiss()
    }
}