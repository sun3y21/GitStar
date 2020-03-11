package com.sunshine.gitstar.ui.main.trendingDevs

import android.os.Bundle
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
import com.sunshine.gitstar.data.developers.Developer


class TrendingDevsFragment : Fragment() {

    private lateinit var developerViewModel: DeveloperViewModel
    private var errorScreen: View? = null
    private lateinit var devAdapter: DeveloperRVAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        developerViewModel = ViewModelProviders.of(this).get(DeveloperViewModel::class.java)
        developerViewModel.getTrendingDevelopers().observe(this, Observer<List<Developer>> { devs: List<Developer> ->
            devAdapter.setDeveloperList(devs)
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            errorScreen?.visibility = View.GONE
        })

        developerViewModel.getDataFetchStatus().observe(this, Observer { isSuccess ->
            progressBar.visibility = View.GONE
            if(!isSuccess)
            {
                errorScreen?.visibility = View.VISIBLE
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val root = inflater.inflate(R.layout.fragment_devs, container, false)
        errorScreen = root.findViewById(R.id.error_screen)
        recyclerView = root.findViewById(R.id.item_list)
        progressBar = root.findViewById(R.id.progress_bar)
        devAdapter = DeveloperRVAdapter(context!!)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = devAdapter
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(): TrendingDevsFragment {
            return TrendingDevsFragment()
        }
    }
}