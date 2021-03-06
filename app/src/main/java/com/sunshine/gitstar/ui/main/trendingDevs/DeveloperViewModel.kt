package com.sunshine.gitstar.ui.main.trendingDevs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.gitstar.data.developers.Developer
import com.sunshine.gitstar.services.GitHubTrendsServiceFactory
import kotlinx.coroutines.launch

class DeveloperViewModel : ViewModel() {
    private val trendingDevelopers: MutableLiveData<List<Developer>> by lazy {
        MutableLiveData<List<Developer>>().also {
            fetchTrendingDevelopers()
        } }

    private val callSuccess : MutableLiveData<Boolean> = MutableLiveData()

    val filteredDevList = MutableLiveData<List<Developer>>()


    fun getTrendingDevelopers() : LiveData<List<Developer>>
    {
        return trendingDevelopers
    }

    fun getDataFetchStatus() : LiveData<Boolean>
    {
        return callSuccess
    }

    private fun fetchTrendingDevelopers()
    {
        viewModelScope.launch {
            try
            {
                val trendingDevs = GitHubTrendsServiceFactory.getGitHubTrendsService().getTrendingDevelopers()
                trendingDevelopers.value = trendingDevs
                callSuccess.value = true
            }
            catch (ex: Exception)
            {
                callSuccess.value = false
            }
        }
    }

    fun filterDevs(text: String)
    {
        val tempFilteredDev = ArrayList<Developer>()
        if(trendingDevelopers.value != null)
        {
            for(dev in trendingDevelopers.value!!)
            {
                if(dev.name != null && dev.name.contains(text,true))
                    tempFilteredDev.add(dev)
            }
            filteredDevList.value = tempFilteredDev
        }
    }
}