package software.yesaya.devfest.ui.posts

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import software.yesaya.devfest.data.db.DevFestDatabase

class HomeViewModelFactory(
    private val dataSource: DevFestDatabase,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DevFestViewModel::class.java))
            return DevFestViewModel(dataSource, application) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}