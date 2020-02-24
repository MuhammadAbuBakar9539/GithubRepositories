package com.example.githubrepositories.model


import com.google.gson.annotations.SerializedName

data class SearchRepositoriesResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int // 30159
) {
    data class Item(
        @SerializedName("name")
        val name: String // tetr.js
    )
}