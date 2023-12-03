package ua.olehkv.pethome.retrofit


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import ua.olehkv.pethome.models.PetModel
import ua.olehkv.pethome.retrofit.entities.LogInRequestBody
import ua.olehkv.pethome.retrofit.entities.LogInResponseBody
import ua.olehkv.pethome.retrofit.entities.SignUpRequestBody
import ua.olehkv.pethome.retrofit.entities.SignUpResponseBody


interface MainApi {
    @GET("/petCardsInfo")
    @Headers(
        "Host: pethomeback.onrender.com",
        "Accept: */*",
        "Accept-Language: en-US,en;q=0.5",
        "Content-Type: application/json")
    suspend fun getPetCardsInfo(
        @Query("selectedPets") selectedPets: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Response<List<PetModel>>

    @POST("/login")
    suspend fun logInUser(
        @Body logInRequestBody: LogInRequestBody
    ) : Response<LogInResponseBody>

    @POST("/register")
    suspend fun signUpUser(
        @Body signUpRequestBody: SignUpRequestBody
    ) : Response<SignUpResponseBody>

//    @POST("/register")
//    fun registerUser(@Body requestBody: YourRequestBodyClass?): Call<YourResponseType?>?

//    @POST("/login")
//    fun loginUser(@Body requestBody: YourRequestBodyClass?): Call<YourResponseType?>?

    // Add other endpoints as needed
}