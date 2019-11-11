package jp.co.musclecamp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusclePost(
    val id: Int,
    val name: String,
    val icon: String,
    val identity: String,
    @Json(name = "muscle_post_body") val musclePostBody: MusclePostBody
)

