package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_PARTICIPANT
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_PARTICIPANT)
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