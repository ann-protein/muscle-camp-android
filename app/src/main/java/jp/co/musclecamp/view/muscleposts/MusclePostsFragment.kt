package jp.co.musclecamp.view.muscleposts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.musclecamp.R
import jp.co.musclecamp.data.Repository
import jp.co.musclecamp.databinding.FragmentMusclePostsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MusclePostsFragment : Fragment(R.layout.fragment_muscle_posts), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
    lateinit var binding: FragmentMusclePostsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMusclePostsBinding.bind(view).also {
            it.lifecycleOwner = viewLifecycleOwner
        }

        val adapter = MusclePostAdapter()
        binding.recyclerView.adapter = adapter
//        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        launch {
            val result = Repository.getMusclePosts()
            if (result.isSuccessful){
                val musclePosts = result.body()?.musclePosts ?: return@launch
                if (musclePosts.isEmpty()) {
                    binding.noPostsMessage.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                else {
                    binding.noPostsMessage.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter.submitList(musclePosts)
                }
            }
        }

        binding.postButton.setOnClickListener {
            findNavController().navigate(R.id.action_muscle_posts_to_send_muscle_post)
        }
    }
}
