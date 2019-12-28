package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Participant (
    @PrimaryKey
    var participant_id : Int,
    var participant_name : String,
    var participant_email : String,
    var user_id : Int?,
    var user_username : String?,
    var user_email : String?,
    var participant_telp : String?,
    var participant_address : String?
) : Parcelable