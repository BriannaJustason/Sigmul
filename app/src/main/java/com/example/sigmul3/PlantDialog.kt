package com.delasign.samplestarterproject.utils.data.com.example.sigmul3

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.sigmul3.R

class PlantDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plant_dialog)

        val closeButton = findViewById<Button>(R.id.closeButton)

        closeButton.setOnClickListener {
            dismiss() // Close the dialog when the Close button is clicked
        }
    }
}