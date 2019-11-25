package jp.co.musclecamp.view.muscleposts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.co.musclecamp.R
import kotlinx.android.synthetic.main.fragment_muscle_posts.*

class MusclePostsFragment : Fragment(R.layout.fragment_muscle_posts) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postButton.setOnClickListener {
            val title = "ムキムキになる伝説のメニュー"
            val body = "腹筋3回"
            val bodyParts = listOf("上腕二頭筋", "腹筋")
        }
    }
}
