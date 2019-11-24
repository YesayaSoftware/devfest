package software.yesaya.devfest.utils

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError", "posts")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, posts: Any?) {
    view.visibility = if (posts != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}