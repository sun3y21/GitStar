package com.sunshine.gitstar.services

import androidx.annotation.WorkerThread
import com.sunshine.gitstar.data.developers.Developer
import com.sunshine.gitstar.data.repository.Repository
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface GitHubTrendsService {

    @GET("repositories")
    suspend fun getTrendingRepositories() : List<Repository>

    @GET("developers")
    suspend fun getTrendingDevelopers() : List<Developer>

    companion object
    {
        const val base_url = "https://github-trending-api.now.sh/"
    }
}