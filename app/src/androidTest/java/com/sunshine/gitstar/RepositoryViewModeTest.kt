package com.sunshine.gitstar

import android.os.Handler
import android.os.Looper
import com.sunshine.gitstar.ui.main.trendingRepos.RepositoryViewModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepositoryViewModeTest
{

    lateinit var repositoryViewModel: RepositoryViewModel
    @Before
    fun setUp()
    {
        repositoryViewModel = RepositoryViewModel()
    }

    @Test
    fun testDataFetchStatusFlag()
    {
        assertTrue(repositoryViewModel.getDataFetchStatus().value == null)
        Handler(Looper.getMainLooper()).post {
            repositoryViewModel.getTrendingRepos().observeForever {
                //on result flag should get set to hide/show UI elements
                assertTrue(repositoryViewModel.getDataFetchStatus().value != null)
            }
        }
    }

    @Test
    fun testDataFetchStatusForSuccessfullDataFetch()
    {
        Handler(Looper.getMainLooper()).post {
            repositoryViewModel.getTrendingRepos().observeForever {
                assertTrue(repositoryViewModel.getDataFetchStatus().value == it.isNotEmpty())
            }
        }
    }

    @Test
    fun testDataFilterStatus()
    {
        assertTrue(repositoryViewModel.filteredRepoList.value == null)
        Handler(Looper.getMainLooper()).post {
            repositoryViewModel.getTrendingRepos().observeForever{

                repositoryViewModel.filterList("xyz")
                assertTrue(repositoryViewModel.filteredRepoList.value != null)
            }
        }
    }
}