package com.sunshine.gitstar.ui.main.trendingRepos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunshine.gitstar.R

class RepositoryDetailViewFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoryDetailViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.repository_detail_view_fragment, container, false)
    }

}
