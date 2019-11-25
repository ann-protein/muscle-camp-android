package jp.co.musclecamp.data

import jp.co.musclecamp.model.AccountRegister
import jp.co.musclecamp.model.MusclePostSender
import jp.co.musclecamp.model.SignInCredential
import jp.co.musclecamp.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @RequireAuth
    @GET("/user/{user_name}")
    suspend fun getUser(@Path("user_name") userName: String): Response<Unit>

    @POST("/users/login")
    suspend fun signIn(@Body signInCredential: SignInCredential): Response<Token>

    @POST("/users")
    suspend fun signUp(@Body accountRegister: AccountRegister): Response<Unit>

    @POST("/muscle_posts")
    suspend fun postMuscle(@Body musclePostSender: MusclePostSender): Response<Unit>
}