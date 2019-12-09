package jp.co.musclecamp.model

data class MusclePostSender(
    val title: String,
    val body: String,
    val body_parts: List<String>
)