package com.sunshine.gitstar.ui.main.trendingRepos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunshine.gitstar.GlideApp
import com.sunshine.gitstar.R
import com.sunshine.gitstar.data.developers.Developer
import com.sunshine.gitstar.data.repository.BuiltBy
import com.sunshine.gitstar.data.repository.Repository
import com.sunshine.gitstar.ui.main.trendingDevs.DeveloperRVAdapter

class RepositoryDetailViewFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoryDetailViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.repository_detail_view_fragment, container, false)
        val repository = arguments?.getParcelable<Repository>(TrendingReposFragment.REPOSITORY)!!
        view.findViewById<TextView>(R.id.repo_name).text = repository.name
        view.findViewById<TextView>(R.id.repo_url).text = repository.url
        view.findViewById<TextView>(R.id.repo_description).text = repository.description
        view.findViewById<TextView>(R.id.repo_fork).text = repository.forks.toString()
        view.findViewById<TextView>(R.id.repo_author).text = context!!.getString(R.string.author,repository.author)
        view.findViewById<TextView>(R.id.repo_star).text = repository.stars.toString()
        if(repository.language != null)
        {
            view.findViewById<TextView>(R.id.repo_language).text = context!!.getString(R.string.language,repository.language)
        }
        GlideApp.with(context!!).load(repository.avatar).placeholder(R.drawable.dummy_repo).error(R.drawable.dummy_repo).into(view.findViewById(R.id.repo_avatar))
        val recyclerView = view.findViewById<RecyclerView>(R.id.built_by)
        if(repository.builtBy != null)
        {
            val developersList = ArrayList<Developer>()
            repository.builtBy.forEach { developersList.add(builtByToDeveloperConverter(it)) }
            val devAdapter = DeveloperRVAdapter(context!!, true)
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = devAdapter
            devAdapter.setDeveloperList(developersList)
        }
        return view
    }

    private fun builtByToDeveloperConverter(builtBy: BuiltBy) : Developer
    {
        return Developer(builtBy.username!!,builtBy.username,builtBy.href,builtBy.avatar,null,null)
    }

}
