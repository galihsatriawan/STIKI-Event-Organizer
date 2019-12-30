package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_INVITATION
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_INVITATION)
data class Invitation(
    @PrimaryKey
    var invitation_id :Int,
    var participant_id : Int,
    var participant_email : String,
    var event_id : Int,
    var arrived_time : String?,
    var status : String?
):Parcelable