package id.shobrun.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ApiResponse
import id.shobrun.stikieventorganizer.api.UserApi
import id.shobrun.stikieventorganizer.models.entity.User
import id.shobrun.stikieventorganizer.models.network.UsersResponse
import id.shobrun.stikieventorganizer.room.UserDao
import id.shobrun.stikieventorganizer.transporter.UserResponseTransporter
import id.shobrun.stikieventorganizer.utils.Helper.md5
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(val apiService: UserApi, val localDB : UserDao,val appExecutors: AppExecutors) {
    fun loginUser(username: String, password:String) = object : NetworkBoundRepository<List<User>,UsersResponse,UserResponseTransporter>(appExecutors){
        override fun saveFetchData(items: UsersResponse) {
            Timber.d("${items.message} ${items.status}")
            if(!items.result.isNullOrEmpty()){
                localDB.insert(items.result[0])
            }
        }

        override fun shouldFetch(data: List<User>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<User>> {
            return localDB.getDetailUserByUsername(username,md5(md5(password)))
        }

        override fun fetchService(): LiveData<ApiResponse<UsersResponse>> {
            val data = hashMapOf(
                "username" to username,
                "password" to md5(password)
            )
            return apiService.loginUser(data)
        }

        override fun transporter(): UserResponseTransporter {
            return UserResponseTransporter()
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }

    }.asLiveData()

}