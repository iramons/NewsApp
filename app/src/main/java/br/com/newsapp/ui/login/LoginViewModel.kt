package br.com.newsapp.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.newsapp.commom.base.CoroutineViewModel
import br.com.newsapp.data.model.APITokenResult
import br.com.newsapp.data.repository.AuthRepository
import br.com.newsapp.data.source.remote.Resource
import timber.log.Timber
import javax.inject.Inject

open class LoginViewModel @Inject constructor(
    var repositoryAuth: AuthRepository
) : CoroutineViewModel() {

    private val _resourceResult = MutableLiveData<Resource<APITokenResult?>>()
    val resourceStatus: LiveData<Resource<APITokenResult?>>
        get() = _resourceResult

    val user: ObservableField<String> = ObservableField("")
    val password: ObservableField<String> = ObservableField("")
    val userLogin: MutableLiveData<String> = MutableLiveData()


    fun onCallLogin() {
        _resourceResult.value = Resource.loading()

        launchIO(block = {
            try {
                val resourceResult = repositoryAuth.login(user = user.get()!!, pass = password.get()!!)
                resourceResult.let {
                    if (it.isSuccessful && it.data != null) {
                        it.data.let {
                            userLogin.postValue(user.get())
                        }
                    }
                    _resourceResult.postValue(resourceResult)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.e(e, e.localizedMessage)
                _resourceResult.postValue(Resource.error(exception = e))
            }
        })
    }
}
