package software.yesaya.devfest.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import software.yesaya.devfest.R
import software.yesaya.devfest.data.db.DevFestDatabase
import software.yesaya.devfest.data.domain.Post
import software.yesaya.devfest.databinding.FragmentDevfestBinding
import software.yesaya.devfest.ui.posts.item.PostAdapter
import software.yesaya.devfest.ui.posts.item.PostItemClick

class DevFestFragment : Fragment() {
    private val devFestViewModel: DevFestViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        val dataSource = DevFestDatabase.getInstance(activity.application)

        val factory = HomeViewModelFactory(dataSource, activity.application)

        ViewModelProvider(this, factory).get(DevFestViewModel::class.java)
    }

    private var postAdapter: PostAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDevfestBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_devfest, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = devFestViewModel

        postAdapter = PostAdapter(PostItemClick { post ->
            val packageManager = context?.packageManager ?: return@PostItemClick

            Toast.makeText(activity, post.title, Toast.LENGTH_LONG).show()
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }

        // Observer for the network error.
        devFestViewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        devFestViewModel.posts.observe(viewLifecycleOwner, Observer<List<Post>> { posts ->
            posts?.apply {
                postAdapter?.posts = posts
            }
        })
    }

    private fun onNetworkError() {
        if(!devFestViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            devFestViewModel.onNetworkErrorShown()
        }
    }
}