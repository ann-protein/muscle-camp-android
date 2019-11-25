package jp.co.musclecamp.view.muscleposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.co.musclecamp.R
import jp.co.musclecamp.model.MusclePost
import kotlinx.android.synthetic.main.item_muscle_post.view.*

class MusclePostAdapter : ListAdapter<MusclePost, RecyclerView.ViewHolder>(MusclePostDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_muscle_post, parent, false)
        return MusclePostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)
        (holder as MusclePostViewHolder).bind(item)
    }

    class MusclePostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(musclePost: MusclePost){
            itemView.musclePostTitle.text = musclePost.musclePostBody.title
        }
    }
}

object MusclePostDiffUtil : DiffUtil.ItemCallback<MusclePost>() {
    override fun areItemsTheSame(oldItem: MusclePost, newItem: MusclePost): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MusclePost, newItem: MusclePost): Boolean {
        return oldItem == newItem
    }
}