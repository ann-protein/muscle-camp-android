package jp.co.musclecamp.data

import jp.co.musclecamp.model.Account
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
    fun getUser(@Path("user_name") userName: String): Response<Unit>

    @POST("/user/")
    fun signIn(@Body signInCredential: SignInCredential): Response<Token>

    @RequireAuth
    @POST("/users/")
    fun signUp(@Body account: Account): Response<Unit>
}
