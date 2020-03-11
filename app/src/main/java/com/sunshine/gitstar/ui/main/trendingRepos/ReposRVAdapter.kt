package com.sunshine.gitstar.ui.main.trendingRepos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunshine.gitstar.R
import com.sunshine.gitstar.data.repository.Repository

class ReposRVAdapter(private val context: Context) : RecyclerView.Adapter<ReposRVAdapter.RepositoryViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)
    private val trendingRepository = ArrayList<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = inflater.inflate(R.layout.repository_list_item, parent, false)
        return RepositoryViewHolder(view)
    }

    fun addRepositories(repos: List<Repository>)
    {
        trendingRepository.clear()
        trendingRepository.addAll(repos)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = trendingRepository.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
    }


    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

    }
}