package com.example.githubrepositories.repository

import com.example.githubrepositories.model.SearchRepositoriesResponse
import com.example.githubrepositories.network.ClientInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryImpl:Repository {
    override fun getSearchedRepositories(topic: String): Observable<SearchRepositoriesResponse> {
        val call = ClientInstance.getClientInstance().getRepositories(topic)

        return call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}