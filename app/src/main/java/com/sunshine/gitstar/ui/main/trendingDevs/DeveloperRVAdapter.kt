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
import com.sunshine.gitstar.GlideApp
import com.sunshine.gitstar.R
import com.sunshine.gitstar.data.developers.Developer

class DeveloperRVAdapter(private val context: Context,val shouldShowSmallTiles: Boolean) : RecyclerView.Adapter<DeveloperRVAdapter.DeveloperViewHolder>()
{
    var inflater: LayoutInflater = LayoutInflater.from(context)
    private val developersList = ArrayList<Developer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder
    {
        val view = if(shouldShowSmallTiles)
        {
            inflater.inflate(R.layout.developer_list_item_small_size,parent,false)
        }
        else
        {
            inflater.inflate(R.layout.developer_list_item,parent,false)
        }
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
        holder.bindData(developersList[position])
    }

    inner class DeveloperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val avatar:ImageView = itemView.findViewById(R.id.dev_avatar)
        private val devName : TextView = itemView.findViewById(R.id.dev_name)
        private val githandle: TextView = itemView.findViewById(R.id.dev_handle)
        private val giturl: TextView = itemView.findViewById(R.id.dev_git_url)

        fun bindData(dev: Developer)
        {
            devName.text = dev.name
            githandle.text = dev.username //it's invisible wasn't looking good
            GlideApp.with(context).load(dev.avatar)
                .apply(RequestOptions().circleCrop())
                .error(R.drawable.dummy_profile)
                .placeholder(R.drawable.dummy_profile)
                .into(avatar)
            giturl.text = dev.url
        }
    }


}