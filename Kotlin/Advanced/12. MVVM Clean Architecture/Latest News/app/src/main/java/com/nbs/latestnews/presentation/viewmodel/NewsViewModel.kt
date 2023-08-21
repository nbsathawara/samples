package com.nbs.latestnews.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.util.Resource
import com.nbs.latestnews.data.util.Utils
import com.nbs.latestnews.domain.usecase.GetNewsHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : AndroidViewModel(app) {

      val newsHeadlines: MutableLiveData<Resource<NewsAPIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            newsHeadlines.postValue(Resource.Loading())
            try {
                if (Utils.isNetworkAvailable(app)) {
                    val apiResponse = getNewsHeadlinesUseCase.execute(country, page)
                    newsHeadlines.postValue(apiResponse)
                } else {
                    newsHeadlines.postValue(Resource.Error("Network not available..."))
                }
            } catch (e: Exception) {
                newsHeadlines.postValue(Resource.Error(e.message.toString()))
            }
        }
    }
}

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlinesUseCase) as T
    }
}