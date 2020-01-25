package id.ac.stiki.doleno.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.ac.stiki.doleno.stikieventorganizer.models.entity.User
import id.ac.stiki.doleno.stikieventorganizer.models.network.UsersResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {
    @POST("user/login")
    fun loginUser(@Body data: HashMap<String, String>): LiveData<ApiResponse<UsersResponse>>

    @POST("user/register")
    fun registerUser(@Body data: HashMap<String, User>): LiveData<ApiResponse<UsersResponse>>

    @POST("user/detail")
    @FormUrlEncoded
    fun getDetailUser(@Field("username") username: String): LiveData<ApiResponse<UsersResponse>>

    @POST("user/edit")
    fun editUser(@Body data: HashMap<String, User>): LiveData<ApiResponse<UsersResponse>>
}