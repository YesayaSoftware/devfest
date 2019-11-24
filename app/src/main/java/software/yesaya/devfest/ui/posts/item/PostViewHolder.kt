package software.yesaya.devfest.ui.posts.item

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import software.yesaya.devfest.R
import software.yesaya.devfest.databinding.DevfestPostItemBinding

class PostViewHolder(val devfestPostItemBinding: DevfestPostItemBinding) :
    RecyclerView.ViewHolder(devfestPostItemBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.devfest_post_item
    }
}
