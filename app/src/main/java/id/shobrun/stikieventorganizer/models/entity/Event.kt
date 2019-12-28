package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @PrimaryKey
    var event_id : Int,
    var user_id : Int,
    var user_username : String,
    var user_email : String,
    var event_name: String,
    var event_description : String,
    var event_date : String,
    var event_location : String?,
    var event_cp : String?,
    var event_status : String
):Parcelable