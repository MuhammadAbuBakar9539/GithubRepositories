package com.example.githubrepositories.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubrepositories.model.SearchRepositoriesResponse
import com.example.githubrepositories.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class SearchRepositoryViewModel(private val repository: Repository) : ViewModel() {

    private val searchedRepository = MutableLiveData<SearchRepositoriesResponse>()
    private val searchedError = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    fun getRepository():MutableLiveData<SearchRepositoriesResponse>{
        return searchedRepository
    }

    fun getError():MutableLiveData<String>{
        return searchedError
    }

    fun getSearchedRepositories(topic: String) {
        compositeDisposable.add(
            repository.getSearchedRepositories(topic).subscribe({ searchResponse ->
                searchedRepository.value = searchResponse
            }, { error ->
                searchedError.value = error.message
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}