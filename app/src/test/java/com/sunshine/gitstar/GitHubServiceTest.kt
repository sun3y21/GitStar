package com.sunshine.gitstar

import com.sunshine.gitstar.services.GitHubTrendsServiceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.Exception

class GitHubServiceTest {

    @Test
    fun testFetchTrendingRepos()
    {
        runBlocking {
            CoroutineScope(Dispatchers.IO).launch{
                try
                {
                    val temp = GitHubTrendsServiceFactory.getGitHubTrendsService().getTrendingRepositories()
                    assertTrue(temp.isNotEmpty())
                }
                catch (ex: Exception)
                {
                    ex.printStackTrace()
                }
            }
        }
    }

    @Test
    fun testFetchTrendingDevs()
    {
        runBlocking {
            CoroutineScope(Dispatchers.IO).launch{
                try
                {
                    val temp = GitHubTrendsServiceFactory.getGitHubTrendsService().getTrendingDevelopers()
                    assertTrue(temp.isNotEmpty())
                }
                catch (ex: Exception)
                {
                    ex.printStackTrace()
                }
            }
        }
    }
}