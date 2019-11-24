package software.yesaya.devfest.data.db.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import software.yesaya.devfest.data.domain.Post

@Entity(tableName = "posts")
data class PostEntry constructor(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "publish_date")
    val publish_date: String
)

fun List<PostEntry>.asDomainModel(): List<Post> {
    return map { post ->
        Post(
            id = post.id,
            title = post.title,
            content = post.content,
            publish_date = post.publish_date
        )
    }
}