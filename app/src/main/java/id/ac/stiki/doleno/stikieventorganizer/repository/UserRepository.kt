package id.ac.stiki.doleno.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.ac.stiki.doleno.stikieventorganizer.AppExecutors
import id.ac.stiki.doleno.stikieventorganizer.api.ApiResponse
import id.ac.stiki.doleno.stikieventorganizer.api.UserApi
import id.ac.stiki.doleno.stikieventorganizer.models.entity.User
import id.ac.stiki.doleno.stikieventorganizer.models.network.UsersResponse
import id.ac.stiki.doleno.stikieventorganizer.room.UserDao
import id.ac.stiki.doleno.stikieventorganizer.transporter.UserResponseTransporter
import id.ac.stiki.doleno.stikieventorganizer.utils.Helper.md5
import id.ac.stiki.doleno.stikieventorganizer.utils.Tools
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
    val apiService: UserApi,
    val localDB: UserDao,
    val appExecutors: AppExecutors
) {
    @Inject
    lateinit var tools: Tools

    fun loginUser(username: String, password: String) = object :
        NetworkBoundRepository<List<User>, UsersResponse, UserResponseTransporter>(appExecutors) {
        override fun saveFetchData(items: UsersResponse) {
            Timber.d("${items.message} ${items.status}")
            if (!items.result.isNullOrEmpty()) {
                localDB.deleteByEmail(username)
                localDB.insert(items.result[0])
            }
        }

        override fun shouldFetch(data: List<User>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<User>> {
            return localDB.getDetailUserByUsername(username, md5(md5(password)))
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

    fun registerUser(user: User) = object :
        NetworkBoundRepository<List<User>, UsersResponse, UserResponseTransporter>(appExecutors) {
        override fun saveFetchData(items: UsersResponse) {
            if (!items.result.isNullOrEmpty()) {
                Timber.d("${items.result[0]}")
                localDB.insert(items.result[0])
            }
        }

        override fun shouldFetch(data: List<User>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<User>> {
            return localDB.getDetailUserByUsername(user.user_username, md5(md5(user.user_password)))
        }

        override fun fetchService(): LiveData<ApiResponse<UsersResponse>> {
            val tempUser = user.copy()
            tempUser.user_password = md5(user.user_password)
            val data = hashMapOf(
                "user" to tempUser
            )
            return apiService.registerUser(data)
        }

        override fun transporter(): UserResponseTransporter {
            return UserResponseTransporter()
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }
    }.asLiveData()

}