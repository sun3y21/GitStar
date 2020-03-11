package com.sunshine.gitstar.ui.main.trendingDevs

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.sunshine.gitstar.data.developers.Developer
import com.sunshine.gitstar.ui.main.BaseListFragment


class TrendingDevsFragment : BaseListFragment() {

    private lateinit var developerViewModel: DeveloperViewModel
    private lateinit var devAdapter: DeveloperRVAdapter
    private val trendingDevelopers = ArrayList<Developer>()

    override fun onCreate()
    {
        devAdapter = DeveloperRVAdapter(context!!)
        developerViewModel = ViewModelProviders.of(this).get(DeveloperViewModel::class.java)
        developerViewModel.getTrendingDevelopers().observe(this, Observer<List<Developer>> { devs: List<Developer> ->
            devAdapter.setDeveloperList(devs)
            trendingDevelopers.clear()
            trendingDevelopers.addAll(devs)
            showItemListing()
        })

        developerViewModel.getDataFetchStatus().observe(this, Observer { isSuccess ->
            manageViewsAfterDataFetch(isSuccess)
        })

        developerViewModel.filteredDevList.observe(this, Observer { filteredDevs ->
            if(isListFiltered())
            {
                devAdapter.setDeveloperList(filteredDevs)
            }
        })
    }

    override fun getAdapter(): RecyclerView.Adapter<*> = devAdapter

    override fun onSearchTextSubmit(text: String?) {
        //set the source to a new filtered list
        if(text != null)
        {
            filteredText = text
            setIsListFilteredStatus(true)
            developerViewModel.filterDevs(text)
        }

    }

    override fun onSearchEnd() {
        //restore original content
        devAdapter.setDeveloperList(trendingDevelopers)
        setIsListFilteredStatus(false)
    }

    override fun clearFilter() {
        devAdapter.setDeveloperList(trendingDevelopers)
    }

    companion object {

        @JvmStatic
        fun newInstance(): TrendingDevsFragment {
            return TrendingDevsFragment()
        }
    }
}