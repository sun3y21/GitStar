package com.sunshine.gitstar.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sunshine.gitstar.R
import com.sunshine.gitstar.data.developers.Developer
import com.sunshine.gitstar.data.repository.Repository


class TrendingDevsFragment : Fragment() {

    private lateinit var developerViewModel: DeveloperViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        developerViewModel = ViewModelProviders.of(this).get(DeveloperViewModel::class.java)
        developerViewModel.getTrendingDevelopers().observe(this, Observer<List<Developer>> { devs: List<Developer> ->
            Log.d("Sunnny: "," "+devs)
        })

        developerViewModel.getDataFetchStatus().observe(this, Observer { isSuccess ->
            if(!isSuccess)
            {
                Log.d("Sunnny","Show Network Error")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val root = inflater.inflate(R.layout.fragment_devs, container, false)
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(): TrendingDevsFragment {
            return TrendingDevsFragment()
        }
    }
}