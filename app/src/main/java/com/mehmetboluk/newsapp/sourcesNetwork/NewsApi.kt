package com.mehmetboluk.newsapp.sourcesNetwork

import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.News
import com.mehmetboluk.newsapp.sourcesNetwork.modelSources.Sources
import com.mehmetboluk.newsapp.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    //https://newsapi.org/v2/sources?apiKey=41d76a02215d4e479b3177928fab2a0f

    @GET("/v2/sources")
    suspend fun getSource(@Query("country")
        countryCode : String = "us",
        @Query("apiKey")
        apiKey : String = API_KEY
    ) : Sources

    @GET("/v2/top-headlines")
    suspend fun getNews(@Query("sources")
        sourceQuery : String,
        @Query("apiKey")
        apiKey : String = API_KEY
    ) : News
}