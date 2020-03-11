package com.sunshine.gitstar.ui.main.trendingRepos

import com.sunshine.gitstar.data.repository.Repository

interface OnItemClickListener {
    fun onItemClicked(repo: Repository)
}