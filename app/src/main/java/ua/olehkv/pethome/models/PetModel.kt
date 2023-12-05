package ua.olehkv.pethome.models

data class PetModel(
    val pet_id: Int,
    val name: String,
    val city: String,
    val country: String,
    val first_pet_photo: String
)
