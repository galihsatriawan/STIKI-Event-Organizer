package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_USER
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_USER)
data class User(
    @PrimaryKey
    var user_id : Int,
    var user_username : String,
    var user_email : String,
    var user_name : String,
    var user_telp : String?,
    var user_address : String?,
    var user_password : String,
    var is_active : Boolean
):Parcelable