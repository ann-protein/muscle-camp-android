package jp.co.musclecamp.view.muscleposts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import jp.co.musclecamp.R
import jp.co.musclecamp.data.Repository
import kotlinx.android.synthetic.main.fragment_muscle_posts.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MusclePostsFragment : Fragment(R.layout.fragment_muscle_posts), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postButton.setOnClickListener {
            val title = "ムキムキになる伝説のメニュー"
            val body = "腹筋3回"
            val bodyParts = listOf("上腕二頭筋", "腹筋")

            launch {
                val response = Repository.postMuscle(title, body, bodyParts)
                if (response.isSuccessful) {

                } else {
                    Log.e("", response.errorBody().toString())
                }
            }
        }
    }
}
