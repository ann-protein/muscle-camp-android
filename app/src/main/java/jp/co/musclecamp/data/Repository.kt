package jp.co.musclecamp.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import jp.co.musclecamp.BuildConfig
import jp.co.musclecamp.model.Account
import jp.co.musclecamp.model.SignInCredential
import jp.co.musclecamp.model.Token
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Repository {
    private val apiService: ApiService by lazy { createService() }

    suspend fun signUp(account: Account): Response<Unit> {
        return apiService.signUp(account)
    }

    suspend fun signIn(email: String, password: String): Response<Token> {
        return apiService.signIn(
            SignInCredential(email, password)
        )
    }

    private fun createService(): ApiService {
        val apiUrl = BuildConfig.API_BASE_URL
        val client = builderHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun builderHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(AuthInterceptor)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logging)
        }

        return client.build()
    }

}
