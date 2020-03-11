package com.sunshine.gitstar.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunshine.gitstar.R

abstract class BaseListFragment : Fragment()
{
    private var errorScreen: View? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var searchActionView: SearchView? = null

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        onCreate()
    }

    fun showItemListing() {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorScreen?.visibility = View.GONE
    }

    fun manageViewsAfterDataFetch(isSuccess: Boolean) {
        progressBar.visibility = View.GONE
        if (!isSuccess) {
            errorScreen?.visibility = View.VISIBLE
        }
    }

    abstract fun getAdapter() : RecyclerView.Adapter<*>
    abstract fun onCreate()
    abstract fun onSearchTextSubmit(text: String?)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val root = inflater.inflate(R.layout.fragment_base, container, false)
        errorScreen = root.findViewById(R.id.error_screen)
        recyclerView = root.findViewById(R.id.item_list)
        progressBar = root.findViewById(R.id.progress_bar)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = getAdapter()
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchViewItem = menu?.findItem(R.id.search)
        searchActionView = searchViewItem?.actionView as SearchView
        searchActionView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean
            {
                onSearchTextSubmit(query)
                searchActionView?.clearFocus()
                searchViewItem.collapseActionView()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                return true
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.search)
        {
            searchActionView?.onActionViewExpanded()
        }
        return super.onOptionsItemSelected(item)
    }
}