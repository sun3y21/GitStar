package com.sunshine.gitstar.services

import Developer
import Repository
import androidx.annotation.WorkerThread
import retrofit2.Call
import retrofit2.http.GET

interface GitHubTrendsService {

    @WorkerThread
    @GET("repositories")
    fun getTrendingRepositories() : Call<List<Repository>>

    @WorkerThread
    @GET("developers")
    fun getTrendingDevelopers() : Call<List<Developer>>

    companion object
    {
        const val base_url = "https://github-trending-api.now.sh/"
    }
}