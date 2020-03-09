package com.sunshine.gitstar.services

object GitHubTrendsServiceFactory {

    private var mGitHubTrendingService: GitHubTrendsService = RetrofitClient.getClient(GitHubTrendsService.base_url).create(GitHubTrendsService::class.java)

    fun getGitHubTrendsService() : GitHubTrendsService
    {
       return mGitHubTrendingService
    }
}