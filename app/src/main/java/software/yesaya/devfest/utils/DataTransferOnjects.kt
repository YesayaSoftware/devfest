package software.yesaya.devfest.utils

import com.squareup.moshi.JsonClass
import software.yesaya.devfest.data.db.modal.PostEntry
import software.yesaya.devfest.data.domain.Post

@JsonClass(generateAdapter = true)
data class NetworkPostContainer(val posts: List<NetworkPost>)

@JsonClass(generateAdapter = true)
data class NetworkPost(
    val id: Int,
    val title: String,
    val content: String,
    val publish_date: String
)

fun NetworkPostContainer.asDomainModel(): List<Post> {
    return posts.map { post ->
        Post(
            id = post.id,
            title = post.title,
            content = post.content,
            publish_date = post.publish_date
        )
    }
}

fun NetworkPostContainer.asDatabaseModel(): Array<PostEntry> {
    return posts.map { post ->
        PostEntry(
            id = post.id,
            title = post.title,
            content = post.content,
            publish_date = post.publish_date
        )
    }.toTypedArray()
}