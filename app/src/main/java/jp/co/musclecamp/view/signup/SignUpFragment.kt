package jp.co.musclecamp.view.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.musclecamp.R
import jp.co.musclecamp.data.Repository
import jp.co.musclecamp.model.Account
import jp.co.musclecamp.model.User
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class SignUpFragment : Fragment(R.layout.fragment_sign_up), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButton.setOnClickListener {
            val account = Account(
                User(
                    "",
                    "",
                    "Hello",
                    "MukiMuki${Random.nextInt(10000)}",
                    emailEditText.text.toString()
                ),
                password = passwordEditText.text.toString()
            )

            signUp(account)
        }
    }

    private fun signUp(account: Account) {
        launch {
            val signUpResponse = Repository.signUp(account)
            if (signUpResponse.isSuccessful.not()) return@launch

            val signInResponse = Repository.signIn(account.user.email, account.password)
            if (signInResponse.isSuccessful) {
                Repository.saveToken(signInResponse.body()!!.token)
                findNavController().navigate(R.id.action_sign_up_to_muscle_posts)
            }
        }
    }
}
