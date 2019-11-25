package jp.co.musclecamp.data

import android.preference.PreferenceManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import jp.co.musclecamp.BuildConfig
import jp.co.musclecamp.model.*
import jp.co.musclecamp.view.MyApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

object Repository {
    private val apiService: ApiService by lazy { createService() }
    private val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MyApplication.instance)

    suspend fun signUp(account: Account): Response<Unit> {
        return apiService.signUp(
            AccountRegister(account)
        )
    }

    suspend fun signIn(email: String, password: String): Response<Token> {
        return apiService.signIn(
            SignInCredential(email, password)
        )
    }

    suspend fun postMuscle(title: String, body: String, bodyParts: List<String>): Response<Unit> {
        return apiService.postMuscle(
            MusclePostSender(
                title,
                body,
                bodyParts
            )
        )
    }

    fun getToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)

    fun saveToken(token: String) {
        sharedPreferences
            .edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }


    private fun createService(): ApiService {
        val apiUrl = BuildConfig.API_BASE_URL
        val client = builderHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
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

    private const val KEY_TOKEN: String = "TokenString"
}
