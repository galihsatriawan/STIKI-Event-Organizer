package id.shobrun.stikieventorganizer.ui.user.register

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

class RegisterViewModel @Inject constructor(repository: UserRepository): ViewModel(){
    val name = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val telp = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val registerAction = MutableLiveData<Boolean>()
    val loginAction = MutableLiveData<Boolean>()
    private val _snackbarText = MutableLiveData<String>()
    private val userMutable = MutableLiveData<User>()
    val snackbarText:LiveData<String> = _snackbarText
    val loading : LiveData<Boolean>
    val isSuccess = MutableLiveData<Boolean>()
    val registerResponse : LiveData<Resource<List<User>,UsersResponse>>
    init {
        registerResponse = userMutable.switchMap {
            userMutable.value?.let {
                repository.registerUser(it)
            }?: AbsentLiveData.create()
        }
        loading = registerResponse.switchMap {
            var isLoading = it.status == Status.LOADING
            if(!isLoading) {
                Timber.d("${it.message?:it.additionalData?.message}")
                _snackbarText.value = it.message?:it.additionalData?.message
                if(!it.data.isNullOrEmpty()) isSuccess.value = true
                Timber.d("${it.data?.size}")
            }
            MutableLiveData(isLoading)
        }
    }

    fun clickRegisterUser(){
        val currentName = name.value
        val currentUsername = username.value
        val currentPassword = password.value
        val currentEmail = email.value
        val currentTelp = telp.value
//        val currentAddress = address.value

        if(currentName.isNullOrEmpty() ||currentPassword.isNullOrEmpty() || currentUsername.isNullOrEmpty() || currentEmail.isNullOrEmpty() || currentTelp.isNullOrEmpty()){
            _snackbarText.value = "Please fill completely"
            return
        }
        if(registerAction.value==null) registerAction.value = true
        else registerAction.value = !registerAction.value!!
        val user = User(null,currentUsername,currentEmail,currentName,currentTelp,"",currentPassword,true)
        userMutable.value = user
    }
    fun clickLogin(){
        if(loginAction.value==null) loginAction.value = true
        else loginAction.value = !loginAction.value!!
    }
}