package com.sunshine.gitstar.ui.main.trendingRepos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.gitstar.data.repository.Repository
import com.sunshine.gitstar.services.GitHubTrendsServiceFactory
import kotlinx.coroutines.launch

class RepositoryViewModel : ViewModel() {

    private val trendingRepository: MutableLiveData<List<Repository>> by lazy {
                                     MutableLiveData<List<Repository>>().also {
                                         fetchTrendingRepository()
                                     } }

    private val callSuccess : MutableLiveData<Boolean> = MutableLiveData()


    fun getTrendingRepos() : LiveData<List<Repository>>
    {
        return trendingRepository
    }

    fun getDataFetchStatus() : LiveData<Boolean>
    {
        return callSuccess
    }


    private fun fetchTrendingRepository()
    {
         viewModelScope.launch {
             try
             {
                 val trendingRepos = GitHubTrendsServiceFactory.getGitHubTrendsService().getTrendingRepositories()
                 trendingRepository.value = trendingRepos
                 callSuccess.value = true
             }
             catch (ex: Exception)
             {
                 callSuccess.value = false
             }
         }
    }
}


