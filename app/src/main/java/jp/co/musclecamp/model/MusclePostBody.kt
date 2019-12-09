package jp.co.musclecamp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusclePostBody(
    val title: String,
    val body: String,
    @Json(name = "body_parts") val bodyParts: List<String>
)
