package jp.co.musclecamp.view.muscleposts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.co.musclecamp.R
import jp.co.musclecamp.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MusclePostsFragment : Fragment(R.layout.fragment_muscle_posts), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launch {
            Repository.getMusclePosts()
        }
    }
}
