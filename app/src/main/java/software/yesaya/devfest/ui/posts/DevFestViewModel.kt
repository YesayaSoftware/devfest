package software.yesaya.devfest.ui.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import software.yesaya.devfest.data.db.DevFestDatabase
import software.yesaya.devfest.data.repositories.PostRepository
import java.io.IOException

class DevFestViewModel(
    private val dataSource: DevFestDatabase,
    application: Application
) : AndroidViewModel(application) {

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val postsRepository = PostRepository(dataSource)

    init {
        viewModelScope.launch {
            try {
                postsRepository.refreshPosts(application.applicationContext)

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                _eventNetworkError.value = true
            }
        }
    }

    val posts = postsRepository.posts

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}