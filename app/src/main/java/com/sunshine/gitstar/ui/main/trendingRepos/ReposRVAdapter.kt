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

class ReposRVAdapter(private val context: Context,private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ReposRVAdapter.RepositoryViewHolder>() {

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
        holder.bind(trendingRepository[position],itemClickListener)
    }


    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val repoAvatar: ImageView = itemView.findViewById(R.id.repo_avatar)
        private val repoName: TextView = itemView.findViewById(R.id.repo_name)
        private val repoAuthor: TextView = itemView.findViewById(R.id.repo_author)
        private val repoDescription: TextView = itemView.findViewById(R.id.repo_description)
        private val repoStar: TextView = itemView.findViewById(R.id.repo_star)
        private val repoFork: TextView = itemView.findViewById(R.id.repo_fork)

        fun bind(repo : Repository,clickListener: OnItemClickListener)
        {
            repoAuthor.text = context.getString(R.string.author,repo.author)
            repoDescription.text = repo.description
            repoName.text = repo.name
            repoFork.text = "${repo.forks}"
            repoStar.text = "${repo.stars}"
            GlideApp.with(context).load(repo.avatar)
                .apply(RequestOptions().circleCrop())
                .error(R.drawable.dummy_repo)
                .placeholder(R.drawable.dummy_repo)
                .into(repoAvatar)
             itemView.setOnClickListener {
                 clickListener.onItemClicked(repo)
             }
        }
    }
}