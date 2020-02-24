package com.example.githubrepositories.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.repository.Repository
import com.example.githubrepositories.view_model.SearchRepositoryViewModel

@Suppress("UNCHECKED_CAST")
class SearchRepositoryViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchRepositoryViewModel(repository) as T
    }

}