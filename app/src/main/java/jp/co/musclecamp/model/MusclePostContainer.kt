package jp.co.musclecamp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusclePostContainer(
    @Json(name = "muscle_posts") val musclePosts: List<MusclePost>
)