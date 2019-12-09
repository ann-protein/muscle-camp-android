package jp.co.musclecamp.view.muscleposts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.musclecamp.R
import jp.co.musclecamp.data.Repository
import kotlinx.android.synthetic.main.fragment_send_muscle_post.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SendMusclePostFragment : Fragment(R.layout.fragment_send_muscle_post), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shareButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val body = bodyEditText.text.toString()
            val bodyParts = listOf("上腕二頭筋", "腹筋")

            launch {
                val result = Repository.postMuscle(title, body, bodyParts)
                if (result.isSuccessful) {
                    findNavController().popBackStack()
                }
            }
        }
    }
}
