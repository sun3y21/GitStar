package com.sunshine.gitstar.ui.main.trendingRepos

import android.content.Intent
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
        repoAdapter = ReposRVAdapter(context!!,object : OnItemClickListener{
            override fun onItemClicked(repo: Repository)
            {
                val intent = Intent(context, RepositoryDetailViewActivity::class.java)
                intent.putExtra(REPOSITORY, repo)
                intent.putExtra(REPOSITORY_NAME, repo.name)
                startActivity(intent)
            }
        })

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

        repositoryViewModel.filteredRepoList.observe(this, Observer { filteredRepos ->
            if(isListFiltered())
            {
                repoAdapter.addRepositories(filteredRepos)
            }
        })
    }

    override fun getAdapter(): RecyclerView.Adapter<*>
    {
        return repoAdapter
    }

    override fun onSearchTextSubmit(text: String?) {
        if(text != null)
        {
            filteredText = text
            setIsListFilteredStatus(true)
            repositoryViewModel.filterList(text)
        }
    }

    override fun onSearchEnd() {
        //restore original content
        repoAdapter.addRepositories(trendingRepositories)
        setIsListFilteredStatus(false)
    }

    override fun clearFilter() {
        repoAdapter.addRepositories(trendingRepositories)
    }

    companion object {

        const val REPOSITORY = "Repository"
        const val REPOSITORY_NAME = "Repository_name"
        @JvmStatic
        fun newInstance(): TrendingReposFragment {
            return TrendingReposFragment()
        }
    }
}