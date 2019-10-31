package jp.co.musclecamp.view.splash

import androidx.fragment.app.Fragment
import jp.co.musclecamp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SplashFragment : Fragment(R.layout.fragment_splash), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
}
