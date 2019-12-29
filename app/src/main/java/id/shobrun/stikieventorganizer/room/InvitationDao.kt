package id.shobrun.stikieventorganizer.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.ID_INVITATION
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_INVITATION

@Dao
interface InvitationDao {
    /**
     * CRUD Room
     */
    @Query("SELECT * FROM $TABLE_INVITATION WHERE $ID_INVITATION = :id")
    fun getDetailInvitation(id : Int) : LiveData<Invitation>

    @Query("SELECT * FROM $TABLE_INVITATION")
    fun getMyInvitations() : LiveData<List<Invitation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(invitation: Invitation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(invitations : List<Invitation>)

    @Update
    fun update(invitation: Invitation) : Int

    @Query("DELETE FROM ${TABLE_INVITATION} WHERE $ID_INVITATION= :id")
    fun delete(id : Int)

    @Query("DELETE FROM $TABLE_INVITATION")
    fun deletes()
}