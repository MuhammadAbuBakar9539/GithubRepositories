package com.example.githubrepositories.repository

import com.example.githubrepositories.model.SearchRepositoriesResponse
import io.reactivex.Observable

interface Repository {
    fun getSearchedRepositories(topic: String):Observable<SearchRepositoriesResponse>
}