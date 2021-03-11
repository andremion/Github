package com.andremion.github.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andremion.github.R
import com.andremion.github.ui.main.model.RepoModel
import com.google.android.material.snackbar.Snackbar

class MainScreen(
    private val view: View
) {
    private val repoListView: RecyclerView = view.findViewById(R.id.main_repo_list)
    private val progressView: View = view.findViewById(R.id.main_progress)

    private val listAdapter = MainRepoListAdapter()

    init {
        setupListView()
    }

    fun render(state: MainViewState) {
        when (state) {
            is MainViewState.Loading -> {
                progressView.isVisible = true
            }
            is MainViewState.Content -> {
                progressView.isVisible = false
                listAdapter.submitList(state.repos)
            }
            is MainViewState.Error -> {
                progressView.isVisible = false
                showError(state.error)
            }
        }
    }

    private fun setupListView() {
        repoListView.adapter = listAdapter
        repoListView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
    }

    private fun showError(error: Exception) {
        val text = if (error.message.isNullOrBlank()) {
            view.context.getString(R.string.generic_error)
        } else {
            error.message!!
        }
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }

}

private class MainRepoListAdapter : ListAdapter<RepoModel, RepoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.main_repo_list_item, parent, false)
            .let(::RepoViewHolder)

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) =
        holder.bind(getItem(position))

}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoModel>() {

    override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean =
        oldItem == newItem
}

private class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameView: TextView = itemView.findViewById(R.id.main_repo_name)
    private val descriptionView: TextView = itemView.findViewById(R.id.main_repo_description)
    private val ownerView: TextView = itemView.findViewById(R.id.main_repo_owner)

    fun bind(model: RepoModel) {
        nameView.text = model.name
        descriptionView.text = model.description
        ownerView.text = model.owner
    }
}
