package jp.co.musclecamp.data

import jp.co.musclecamp.model.Account
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @RequireAuth
    @GET("/user/{user_name}")
    fun getUser(@Path("user_name") userName: String): Response<Unit>

    @RequireAuth
    @POST("/user/")
    fun getUser(): Response<Unit>

    @POST("/users/")
    fun signUp(@Body account: Account): Response<Unit>
}
