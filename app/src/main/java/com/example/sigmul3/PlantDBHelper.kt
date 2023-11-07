package com.delasign.samplestarterproject.utils.data.com.example.sigmul3

import DefaultImage
import Plant
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class PlantDBHelper(context: Context) : SQLiteOpenHelper(context, "PlantDB.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = """
            CREATE TABLE plants (
    id INTEGER PRIMARY KEY,
    common_name TEXT,
    scientific_name TEXT,
    other_name TEXT,
    cycle TEXT,
    watering TEXT,
    sunlight TEXT,
    default_image_license INTEGER,
    default_image_license_name TEXT,
    default_image_license_url TEXT,
    default_image_original_url TEXT,
    default_image_regular_url TEXT,
    default_image_medium_url TEXT,
    default_image_small_url TEXT,
    default_image_thumbnail TEXT
);
        """.trimIndent()

        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS plants")
        onCreate(db)
    }

    fun insertPlant(plant: Plant): Long {
        val db = writableDatabase
        val contentValues = ContentValues()

        contentValues.put("commonName", plant.commonName)
        contentValues.put("scientificName", Gson().toJson(plant.scientificName))
        contentValues.put("otherName", Gson().toJson(plant.otherName))
        contentValues.put("cycle", plant.cycle)
        contentValues.put("watering", plant.watering)
        contentValues.put("sunlight", Gson().toJson(plant.sunlight))

        val defaultImage = plant.defaultImage
        contentValues.put("licenseName", defaultImage.licenseName)
        contentValues.put("licenseUrl", defaultImage.licenseUrl)
        contentValues.put("originalUrl", defaultImage.originalUrl)
        contentValues.put("regularUrl", defaultImage.regularUrl)
        contentValues.put("mediumUrl", defaultImage.mediumUrl)
        contentValues.put("smallUrl", defaultImage.smallUrl)
        contentValues.put("thumbnail", defaultImage.thumbnail)

        return db.insert("plants", null, contentValues)
    }

    fun getAllPlants(): List<Plant> {
        val plantList = mutableListOf<Plant>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM plants", null)

        if (cursor.moveToFirst()) {
            do {
                val commonName = cursor.getString(with(cursor) { getColumnIndex("commonName") })
                val scientificNameJson = cursor.getString(with(cursor) { getColumnIndex("scientificName") })
                val otherNameJson = cursor.getString(with(cursor) { getColumnIndex("otherName") })
                val cycle = cursor.getString(with(cursor) { getColumnIndex("cycle") })
                val watering = cursor.getString(with(cursor) { getColumnIndex("watering") })
                val sunlightJson = cursor.getString(with(cursor) { getColumnIndex("sunlight") })
                val defaultImageLicense = cursor.getInt(with(cursor) { getColumnIndex("defaultImageLicense") })
                val defaultImageLicenseName = cursor.getString(with(cursor) { getColumnIndex("defaultImageLicenseName") })
                val defaultImageLicenseUrl = cursor.getString(with(cursor) { getColumnIndex("defaultImageLicenseUrl") })
                val defaultImageOriginalUrl = cursor.getString(with(cursor) { getColumnIndex("defaultImageOriginalUrl") })
                val defaultImageRegularUrl = cursor.getString(with(cursor) { getColumnIndex("defaultImageRegularUrl") })
                val defaultImageMediumUrl = cursor.getString(with(cursor) { getColumnIndex("defaultImageMediumUrl") })
                val defaultImageSmallUrl = cursor.getString(with(cursor) { getColumnIndex("defaultImageSmallUrl") })
                val defaultImageThumbnail = cursor.getString(with(cursor) { getColumnIndex("defaultImageThumbnail") })

                val scientificName = Gson().fromJson<List<String>>(scientificNameJson, List::class.java)
                val otherName = Gson().fromJson<List<String>>(otherNameJson, List::class.java)
                val sunlight = Gson().fromJson<Any>(sunlightJson, Any::class.java) // Handle the varying type

                val plant = Plant(
                    0,
                    commonName,
                    scientificName,
                    otherName,
                    cycle,
                    watering,
                    sunlight,
                    DefaultImage(
                        defaultImageLicense,
                        defaultImageLicenseName,
                        defaultImageLicenseUrl,
                        defaultImageOriginalUrl,
                        defaultImageRegularUrl,
                        defaultImageMediumUrl,
                        defaultImageSmallUrl,
                        defaultImageThumbnail
                    )
                )
                plantList.add(plant)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return plantList
    }
}




