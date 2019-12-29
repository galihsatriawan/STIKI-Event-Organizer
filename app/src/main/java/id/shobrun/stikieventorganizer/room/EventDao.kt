package id.shobrun.stikieventorganizer.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.ID_EVENT
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_EVENT

@Dao
interface EventDao {
    /**
     * CRUD Room
     */
    @Query("SELECT * FROM $TABLE_EVENT WHERE $ID_EVENT = :id")
    fun getDetailEvent(id : Int) : LiveData<Event>

    @Query("SELECT * FROM $TABLE_EVENT")
    fun getMyEvents() : LiveData<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event : Event)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(events : List<Event>)

    @Update
    fun update(event: Event) : Int

    @Query("DELETE FROM $TABLE_EVENT WHERE $ID_EVENT = :id")
    fun delete(id : Int)

    @Query("DELETE FROM $TABLE_EVENT")
    fun deletes()

}