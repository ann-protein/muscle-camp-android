package jp.co.musclecamp.view.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.musclecamp.R
import jp.co.musclecamp.data.Repository
import jp.co.musclecamp.model.TokenManager

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Repository.getToken()?.let {
            signIn(it)
        } ?: goToSignUp()
    }

    private fun signIn(token: String) {
        TokenManager.put(token)
        findNavController().navigate(R.id.action_splash_to_muscle_posts)
    }

    private fun goToSignUp() {
        findNavController().navigate(R.id.action_splash_to_sign_up)
    }
}
