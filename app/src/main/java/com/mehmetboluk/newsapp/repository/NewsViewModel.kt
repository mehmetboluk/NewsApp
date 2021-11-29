package com.mehmetboluk.newsapp.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetboluk.newsapp.db.NewsDatabase
import com.mehmetboluk.newsapp.sourcesNetwork.RetrofitInstance
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.Article
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.News
import com.mehmetboluk.newsapp.sourcesNetwork.modelSources.Sources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel() : ViewModel() {

    private var sourcesNew : MutableLiveData<Sources> = MutableLiveData()
    private var newsTarget : MutableLiveData<News> = MutableLiveData()
    private var newsDatabase : ArrayList<Article> = ArrayList()

    // RoomDatabase

    fun callDataFromRoom(context: Context) : ArrayList<Article>{
        val data = NewsDatabase(context).getNewsDao().getAll()
        newsDatabase.addAll(data)
        return newsDatabase
    }

    fun upsertData(context : Context, article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            NewsDatabase(context).getNewsDao().insert(article)
        }
    }

    fun deleteData(context : Context, article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            NewsDatabase(context).getNewsDao().delete(article)
        }
    }

    // Retrofit Call

    fun makeApiCallForSource(countryCode : String) = viewModelScope.launch(Dispatchers.IO) {
        val response = RetrofitInstance.api.getSource(countryCode)
        sourcesNew.postValue(response)
    }

    fun makeApiCallForNews(sourceQuery : String) = viewModelScope.launch(Dispatchers.IO) {
        val response = RetrofitInstance.api.getNews(sourceQuery)
        newsTarget.postValue(response)
    }

    fun sourcesObserver () : MutableLiveData<Sources> {
        return sourcesNew
    }

    fun newsObserver () : MutableLiveData<News> {
        return newsTarget
    }

}