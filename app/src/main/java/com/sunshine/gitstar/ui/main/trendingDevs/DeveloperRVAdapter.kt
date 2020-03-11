package com.sunshine.gitstar.ui.main.trendingDevs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sunshine.gitstar.R
import com.sunshine.gitstar.data.developers.Developer

class DeveloperRVAdapter(private val context: Context) : RecyclerView.Adapter<DeveloperRVAdapter.DeveloperViewHolder>()
{
    var inflater: LayoutInflater = LayoutInflater.from(context)
    private val developersList = ArrayList<Developer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder
    {
        val view = inflater.inflate(R.layout.developer_list_item,parent,false)
        return DeveloperViewHolder(view)
    }

    fun setDeveloperList(devs: List<Developer>)
    {
        developersList.clear()
        developersList.addAll(devs)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int
    {
        return developersList.size
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int)
    {
        holder.devName.text = developersList[position].name
        holder.githandle.text = developersList[position].username //it's invisible wasn't looking good
        Glide.with(context).load(developersList[position].avatar)
             .apply(RequestOptions().circleCrop())
             .into(holder.avatar)
        holder.giturl.text = developersList[position].url
    }

    inner class DeveloperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val avatar:ImageView = itemView.findViewById(R.id.dev_avatar)
        val devName : TextView = itemView.findViewById(R.id.dev_name)
        val githandle: TextView = itemView.findViewById(R.id.dev_handle)
        val giturl: TextView = itemView.findViewById(R.id.dev_git_url)
    }


}