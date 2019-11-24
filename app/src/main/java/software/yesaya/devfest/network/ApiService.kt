package software.yesaya.devfest.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import software.yesaya.devfest.utils.NetworkPostContainer

interface ApiService {

    @GET("posts")
    fun getPostsAsync(): Deferred<NetworkPostContainer>
}