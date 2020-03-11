package com.sunshine.gitstar.ui.main.trendingRepos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunshine.gitstar.R
import com.sunshine.gitstar.data.repository.Repository
import com.sunshine.gitstar.ui.main.trendingDevs.DeveloperRVAdapter


class TrendingReposFragment : Fragment() {


    private lateinit var repositoryViewModel: RepositoryViewModel
    private var errorScreen: View? = null
    private lateinit var repoAdapter: ReposRVAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        repositoryViewModel.getTrendingRepos().observe(this, Observer<List<Repository>> { repos: List<Repository> ->
            repoAdapter.addRepositories(repos)
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            errorScreen?.visibility = View.GONE
        })

        repositoryViewModel.getDataFetchStatus().observe(this, Observer { isSuccess ->
            progressBar.visibility = View.GONE
            if(!isSuccess)
            {
                errorScreen?.visibility = View.VISIBLE
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val root = inflater.inflate(R.layout.fragment_repos, container, false)
        errorScreen = root.findViewById(R.id.error_screen)
        recyclerView = root.findViewById(R.id.item_list)
        progressBar = root.findViewById(R.id.progress_bar)
        repoAdapter = ReposRVAdapter(context!!)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = repoAdapter
        return root
    }

    companion object {


        @JvmStatic
        fun newInstance(): TrendingReposFragment {
            return TrendingReposFragment()
        }
    }
}