package software.yesaya.devfest.ui.posts.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import software.yesaya.devfest.data.domain.Post
import software.yesaya.devfest.databinding.DevfestPostItemBinding

class PostAdapter (val callback : PostItemClick) : RecyclerView.Adapter<PostViewHolder>() {
    var posts: List<Post> = emptyList()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val withDataBinding: DevfestPostItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PostViewHolder.LAYOUT,
            parent,
            false
        )

        return PostViewHolder(withDataBinding)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.devfestPostItemBinding.also { post ->
            post.post = posts[position]
            post.postCallback = callback
        }
    }
}