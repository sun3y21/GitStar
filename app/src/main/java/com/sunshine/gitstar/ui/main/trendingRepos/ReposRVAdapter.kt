package com.sunshine.gitstar.ui.main.trendingRepos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.sunshine.gitstar.GlideApp
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
        holder.repoAuthor.text = context.getString(R.string.author,trendingRepository[position].author)
        holder.repoDescription.text = trendingRepository[position].description
        holder.repoName.text = trendingRepository[position].name
        holder.repoFork.text = "${trendingRepository[position].forks}"
        holder.repoStar.text = "${trendingRepository[position].stars}"
        GlideApp.with(context).load(trendingRepository[position].avatar)
            .apply(RequestOptions().circleCrop())
            .error(R.drawable.dummy_repo)
            .placeholder(R.drawable.dummy_repo)
            .into(holder.repoAvatar)
    }


    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val repoAvatar: ImageView = itemView.findViewById(R.id.repo_avatar)
        val repoName: TextView = itemView.findViewById(R.id.repo_name)
        val repoAuthor: TextView = itemView.findViewById(R.id.repo_author)
        val repoDescription: TextView = itemView.findViewById(R.id.repo_description)
        val repoStar: TextView = itemView.findViewById(R.id.repo_star)
        val repoFork: TextView = itemView.findViewById(R.id.repo_fork)
    }
}