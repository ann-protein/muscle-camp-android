package jp.co.musclecamp.model

class TokenManager {
    companion object {
        private var token: String? = null

        fun put(value: String) {
            token = value
        }

        fun get(): String? = token
    }
}
