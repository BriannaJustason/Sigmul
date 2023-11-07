package com.delasign.samplestarterproject.utils.data.com.example.sigmul3
import DefaultImage
import Plant
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.gson.Gson

class PlantDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Define the table for storing plant information
        db.execSQL(
            "CREATE TABLE $PLANT_TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_COMMON_NAME TEXT," +
                    "$COLUMN_IMAGE_URL TEXT," +
                    "$COLUMN_EDIBLE TEXT," +
                    "$COLUMN_POISONOUS TEXT," +
                    "$COLUMN_WATER TEXT," +
                    "$COLUMN_SUNLIGHT TEXT" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Upgrade the database if needed
        db.execSQL("DROP TABLE IF EXISTS $PLANT_TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PlantDatabase"
        const val PLANT_TABLE_NAME = "Plants"
        const val COLUMN_ID = "id"
        const val COLUMN_COMMON_NAME = "commonName"
        const val COLUMN_IMAGE_URL = "imageUrl"
        const val COLUMN_EDIBLE = "edible"
        const val COLUMN_POISONOUS = "poisonous"
        const val COLUMN_WATER = "water"
        const val COLUMN_SUNLIGHT = "sunlight"
    }

    fun getAllPlants(): List<Plant> {
        val plantList = mutableListOf<Plant>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM plants", null)

        if (cursor.moveToFirst()) {
            do {
                val commonName = cursor.getString(with(cursor) { getColumnIndex("commonName") })
                val defaultImage = cursor.getString(with(cursor) { getColumnIndex("imageUrl") })
                val edible = cursor.getString(with(cursor) { getColumnIndex("edible") })
                val poisonous = cursor.getString(with(cursor) { getColumnIndex("poisonous") })
                val watering = cursor.getString(with(cursor) { getColumnIndex("water") })
                val sunlightJson = cursor.getString(with(cursor) { getColumnIndex("sunlight") })



                val plant = Plant(
                    0,
                    commonName,
                    defaultImage= DefaultImage(0,"","",defaultImage,defaultImage,defaultImage,defaultImage,defaultImage),
                    edible,
                    poisonous,
                    watering,
                    sunlightJson,
                )
                plantList.add(plant)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return plantList
    }
}


