package id.shobrun.stikieventorganizer.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.ID_PARTICIPANT
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_PARTICIPANT

@Dao
interface ParticipantDao {
    /**
     * CRUD Room
     */
    @Query("SELECT * FROM $TABLE_PARTICIPANT WHERE $ID_PARTICIPANT = :id")
    fun getDetailParticipant(id : String) : LiveData<Participant>

    @Query("SELECT * FROM $TABLE_PARTICIPANT")
    fun getMyParticipants() : LiveData<List<Participant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(participant: Participant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(participants : List<Participant>)

    @Update
    fun update(participant: Participant) : Int

    @Query("DELETE FROM $TABLE_PARTICIPANT WHERE ${ID_PARTICIPANT}= :id")
    fun delete(id : String)

    @Query("DELETE FROM $TABLE_PARTICIPANT")
    fun deletes()
}