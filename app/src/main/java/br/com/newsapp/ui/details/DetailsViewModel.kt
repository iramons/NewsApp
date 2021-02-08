package br.com.newsapp.ui.details

import androidx.lifecycle.*
import br.com.newsapp.commom.base.CoroutineViewModel
import br.com.newsapp.data.model.Document
import br.com.newsapp.data.model.DocumentResponse
import br.com.newsapp.data.repository.DetailsRepository
import br.com.newsapp.data.source.remote.Resource
import timber.log.Timber
import javax.inject.Inject

open class DetailsViewModel @Inject constructor(var repo: DetailsRepository):
        CoroutineViewModel(), LifecycleObserver {

    private val _newsList = MutableLiveData<Resource<List<DocumentResponse>?>>()
    val newsList: LiveData<Resource<List<DocumentResponse>?>> get() = _newsList

    private val _documentList = MutableLiveData<Resource<List<Document>?>>()
    val documentList: LiveData<Resource<List<Document>?>> get() = _documentList
    private var docList = ArrayList<Document>()

    private val _document = MutableLiveData<Resource<Document>?>()
    val document: LiveData<Resource<Document>?> get() = _document

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        super.onCleared()
    }

    fun fetchNewsDetails(id: String) {
        _newsList.value = Resource.loading()
        launchIO {
            try {
                val resourceResult = repo.getDocuments(id)
                resourceResult.let { newsList ->
                    _newsList.postValue(newsList)

                    newsList.data?.forEach { newsData ->
                        val doc = newsData.document
//                        _document.postValue(doc)
//                        addDocumentToList(doc?.data!!)
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.e(e, e.localizedMessage)
                _newsList.postValue(Resource.error(exception = e))
            }
        }
    }

    private fun addDocumentToList(item: Document) {
        docList.add(item)
        _documentList.postValue(docList as Resource<List<Document>?>)
    }
}
