package jp.co.musclecamp.model

class Token {
    companion object {
        private var token: String? = null

        fun put(value: String) {
            token = value
        }

        fun get(): String? = token
    }
}
