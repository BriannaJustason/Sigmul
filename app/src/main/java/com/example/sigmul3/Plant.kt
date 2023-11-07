import com.google.gson.annotations.SerializedName

data class PlantResponse(
    @SerializedName("data") val data: List<Plant>,
    @SerializedName("to") val to: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("from") val from: Int,
    @SerializedName("last_page") val lastPage: Int,
    @SerializedName("total") val total: Int
)

data class Plant(
    @SerializedName("id") val id: Int,
    @SerializedName("common_name") val commonName: String,
    @SerializedName("default_image") val defaultImage: DefaultImage,
    @SerializedName("edible") val edible: String,
    @SerializedName("poisonous") val poisonous: String,
    @SerializedName("watering") val watering: String,
    @SerializedName("sunlight") val sunlight: Any, // Handle the varying type

)

data class DefaultImage(
    @SerializedName("license") val license: Int,
    @SerializedName("license_name") val licenseName: String,
    @SerializedName("license_url") val licenseUrl: String,
    @SerializedName("original_url") val originalUrl: String,
    @SerializedName("regular_url") val regularUrl: String,
    @SerializedName("medium_url") val mediumUrl: String,
    @SerializedName("small_url") val smallUrl: String,
    @SerializedName("thumbnail") val thumbnail: String
)
