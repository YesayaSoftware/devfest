package software.yesaya.devfest.ui.posts.item

import software.yesaya.devfest.data.domain.Post

class PostItemClick (val block : (Post) -> Unit) {
    fun onClick(post: Post) = block(post)
}