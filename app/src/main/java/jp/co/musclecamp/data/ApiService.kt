package jp.co.musclecamp.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/user/")
    fun getUser(): Response<Unit>
}
