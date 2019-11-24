package software.yesaya.devfest.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import software.yesaya.devfest.data.db.DevFestDatabase
import software.yesaya.devfest.data.db.modal.asDomainModel
import software.yesaya.devfest.data.domain.Post
import software.yesaya.devfest.network.ApiService
import software.yesaya.devfest.network.RetrofitBuilder
import software.yesaya.devfest.utils.asDatabaseModel

class PostRepository(private val database: DevFestDatabase) {

    val posts: LiveData<List<Post>> = Transformations.map(database.postDao.selectAll()) { post ->
        post.asDomainModel()
    }

    suspend fun refreshPosts(applicationContext: Context) {
        withContext(Dispatchers.IO) {
            val posts = RetrofitBuilder.createService(
                ApiService::class.java
            ).getPostsAsync().await()

            database.postDao.insert(*posts.asDatabaseModel())
        }
    }
}