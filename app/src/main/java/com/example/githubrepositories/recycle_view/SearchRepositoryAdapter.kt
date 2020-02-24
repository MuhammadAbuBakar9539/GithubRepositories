package com.example.githubrepositories.recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.R
import com.example.githubrepositories.model.SearchRepositoriesResponse
import kotlinx.android.synthetic.main.activity_search_repository.view.*
import kotlinx.android.synthetic.main.search_repository_item.view.*

class SearchRepositoryAdapter(private val repositoryList: SearchRepositoriesResponse) :
    RecyclerView.Adapter<SearchRepositoryAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.search_repository_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return repositoryList.items.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.name.text = repositoryList.items[position].name
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tv_name
    }
}