package com.sunshine.gitstar.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sunshine.gitstar.R
import com.sunshine.gitstar.data.repository.Repository


class TrendingReposFragment : Fragment() {


    private lateinit var repositoryViewModel: RepositoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        repositoryViewModel.getTrendingRepos().observe(this, Observer<List<Repository>> { repos: List<Repository> ->
            Log.d("Sunnny: "," "+repos)
        })

        repositoryViewModel.getDataFetchStatus().observe(this, Observer { isSuccess ->
            if(!isSuccess)
            {
                Log.d("Sunnny","Show Network Error")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val root = inflater.inflate(R.layout.fragment_repos, container, false)
        return root
    }

    companion object {


        @JvmStatic
        fun newInstance(): TrendingReposFragment {
            return TrendingReposFragment()
        }
    }
}