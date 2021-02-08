package br.com.newsapp.ui.news

import androidx.lifecycle.*
import br.com.newsapp.commom.base.CoroutineViewModel
import br.com.newsapp.data.model.News
import br.com.newsapp.data.repository.NewsRepository
import br.com.newsapp.data.source.remote.Resource
import timber.log.Timber
import javax.inject.Inject

open class NewsViewModel @Inject constructor(var repository: NewsRepository):
    CoroutineViewModel(), LifecycleObserver {

    private val _listNews = MutableLiveData<Resource<List<News>?>>()
    val listNews: LiveData<Resource<List<News>?>> get() = _listNews

    fun fetchNewsList() {
        _listNews.value = Resource.loading()
        launchIO {
            try {
                val resourceResult = repository.getNews()
                resourceResult.let {
                    _listNews.postValue(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.e(e, e.localizedMessage)
                _listNews.postValue(Resource.error(exception = e))
            }
        }
    }
}
