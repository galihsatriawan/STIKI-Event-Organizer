package id.shobrun.stikieventorganizer.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.User
import id.shobrun.stikieventorganizer.models.network.UsersResponse
import id.shobrun.stikieventorganizer.repository.UserRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(repository: UserRepository) : ViewModel(){
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val loginAction = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean>
    val isSuccess= MutableLiveData<Boolean>()
    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText
    val loginRespons : LiveData<Resource<List<User>,UsersResponse>>
    init {
        loginRespons = loginAction.switchMap {
            loginAction.value?.let {
                username.value?.let {
                    password.value?.let{
                        repository.loginUser(username.value!!,password.value!!)
                    }
                }

            }?:AbsentLiveData.create()
        }
        loading = loginRespons.switchMap {
            Timber.d("${it.status}")
            val isLoading = it.status==Status.LOADING
            if(!isLoading){
                _snackbarText.value = it.message?: it.additionalData?.message
                if(!it.data.isNullOrEmpty())isSuccess.value = true
            }
            MutableLiveData(isLoading)
        }
    }

    fun clickLogin(){
        if(loginAction.value==null) loginAction.value = true
        else loginAction.value = !loginAction.value!!
    }
}