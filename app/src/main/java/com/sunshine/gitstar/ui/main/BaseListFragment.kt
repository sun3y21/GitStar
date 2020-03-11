package com.sunshine.gitstar.ui.main

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
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
    private var isListFiltered = false
    private var clearFilter: TextView? = null
    private val isListFilteredKey = "IS_LIST_FILTERED"
    private val filteredTextKey = "FILTERED_TEXT"
    var filteredText: String? = null

    fun isListFiltered() : Boolean = isListFiltered

    fun setIsListFilteredStatus(status: Boolean)
    {
        isListFiltered = status
        clearFilter?.visibility = if(status)
        {
            clearFilter?.text = filteredText
            View.VISIBLE
        }
        else
        {
            View.GONE
        }
    }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(isListFilteredKey,isListFiltered)
        outState.putString(filteredTextKey, filteredText)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        isListFiltered = savedInstanceState?.getBoolean(isListFilteredKey) ?: false
        filteredText = savedInstanceState?.getString(filteredTextKey)
    }

    abstract fun getAdapter() : RecyclerView.Adapter<*>
    abstract fun onCreate()
    abstract fun onSearchTextSubmit(text: String?)
    abstract fun onSearchEnd()
    abstract fun clearFilter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val root = inflater.inflate(R.layout.fragment_base, container, false)
        errorScreen = root.findViewById(R.id.error_screen)
        recyclerView = root.findViewById(R.id.item_list)
        progressBar = root.findViewById(R.id.progress_bar)
        clearFilter = root.findViewById(R.id.clear_filter)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = getAdapter()
        if(isListFiltered)
        {
            clearFilter?.text = filteredText
            clearFilter?.visibility = View.VISIBLE
        }
        clearFilter?.setOnClickListener {
            setIsListFilteredStatus(false)
            filteredText = null
            clearFilter?.visibility = View.GONE
            searchActionView?.setQuery("",false)
            clearFilter()
        }
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
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                return true
            }
        })

        searchViewItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener
        {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                onSearchEnd()
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