package com.mehmetboluk.newsapp.sourcesNetwork.modelNews

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)