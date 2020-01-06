package id.shobrun.stikieventorganizer.models.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_INVITATION
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_INVITATION)
data class Invitation(
    @PrimaryKey
    @SerializedName("ID") var invitation_id :Int,
    @SerializedName("PARTICIPANT_ID") var participant_id : Int,
    @SerializedName("PARTICIPANT_EMAIL") var participant_email : String,
    @SerializedName("EVENT_ID") var event_id : Int,
    @SerializedName("ARRIVED_TIME") var arrived_time : String?,
    @SerializedName("STATUS") var status : String?,
    @SerializedName("EVENT_NAME") var event_name: String?,
    @SerializedName("EVENT_DATE") var event_date : String,
    @SerializedName("INVITER") var inviter : String?
):Parcelable