package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_EVENT
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_EVENT)
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
    var event_map_location : String?,
    var event_latitude : Double?,
    var event_longitude : Double?,
    var event_cp : String?,
    var event_status : String
):Parcelable