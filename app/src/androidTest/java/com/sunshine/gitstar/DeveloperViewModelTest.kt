package com.sunshine.gitstar

import android.os.Handler
import android.os.Looper
import com.sunshine.gitstar.ui.main.trendingDevs.DeveloperViewModel
import com.sunshine.gitstar.ui.main.trendingRepos.RepositoryViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DeveloperViewModelTest {
    lateinit var developerViewModel: DeveloperViewModel
    @Before
    fun setUp()
    {
        developerViewModel = DeveloperViewModel()
    }

    @Test
    fun testDataFetchStatusFlag()
    {
        Assert.assertTrue(developerViewModel.getDataFetchStatus().value == null)
        Handler(Looper.getMainLooper()).post {
            developerViewModel.getTrendingDevelopers().observeForever {
                //on result flag should get set to hide/show UI elements
                Assert.assertTrue(developerViewModel.getDataFetchStatus().value != null)
            }
        }
    }

    @Test
    fun testDataFetchStatusForSuccessfullDataFetch()
    {
        Handler(Looper.getMainLooper()).post {
            developerViewModel.getTrendingDevelopers().observeForever {
                Assert.assertTrue(developerViewModel.getDataFetchStatus().value == it.isNotEmpty())
            }
        }
    }

    @Test
    fun testDataFilterStatus()
    {
        Assert.assertTrue(developerViewModel.filteredDevList.value == null)
        Handler(Looper.getMainLooper()).post {
            developerViewModel.getTrendingDevelopers().observeForever{

                developerViewModel.filterDevs("xyz")
                Assert.assertTrue(developerViewModel.filteredDevList.value != null)
            }
        }
    }
}