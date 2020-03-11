package com.sunshine.gitstar.ui.main.trendingRepos

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.sunshine.gitstar.data.repository.Repository
import com.sunshine.gitstar.ui.main.BaseListFragment


class TrendingReposFragment : BaseListFragment() {

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var repoAdapter: ReposRVAdapter
    private val trendingRepositories = ArrayList<Repository>()

    override fun onCreate()
    {
        repoAdapter = ReposRVAdapter(context!!)
        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        repositoryViewModel.getTrendingRepos().observe(this, Observer<List<Repository>> { repos: List<Repository> ->
            repoAdapter.addRepositories(repos)
            trendingRepositories.clear()
            trendingRepositories.addAll(repos)
            showItemListing()
        })

        repositoryViewModel.getDataFetchStatus().observe(this, Observer { isSuccess ->
            manageViewsAfterDataFetch(isSuccess)
        })
    }

    override fun getAdapter(): RecyclerView.Adapter<*>
    {
        return repoAdapter
    }

    override fun onSearchTextSubmit(text: String?) {

    }

    companion object {

        @JvmStatic
        fun newInstance(): TrendingReposFragment {
            return TrendingReposFragment()
        }
    }
}