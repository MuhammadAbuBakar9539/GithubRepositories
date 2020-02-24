package com.example.githubrepositories.network


import com.example.githubrepositories.common.END_POINT
import com.example.githubrepositories.common.QUERY
import com.example.githubrepositories.model.SearchRepositoriesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Client {

    @GET(END_POINT)
    fun getRepositories(@Query(QUERY) topic: String): Observable<SearchRepositoriesResponse>
}