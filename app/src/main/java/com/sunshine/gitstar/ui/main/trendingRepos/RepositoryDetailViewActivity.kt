package com.sunshine.gitstar.ui.main.trendingRepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.sunshine.gitstar.R

class RepositoryDetailViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_detail_view_activity)
        supportActionBar?.title = intent.getStringExtra(TrendingReposFragment.REPOSITORY_NAME)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            val fragment = RepositoryDetailViewFragment.newInstance()
            fragment.arguments = intent.extras
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
