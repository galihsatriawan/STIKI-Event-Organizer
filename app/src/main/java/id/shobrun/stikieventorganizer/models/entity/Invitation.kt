package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Invitation(
    @PrimaryKey
    var invitations_id :Int,
    var participant_id : Int,
    var participant_email : String,
    var event_id : Int,
    var arrived_time : String?,
    var status : String?
):Parcelable