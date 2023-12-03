package ua.olehkv.pethome.retrofit.entities

data class SignUpRequestBody(
    val confpass: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String
)
