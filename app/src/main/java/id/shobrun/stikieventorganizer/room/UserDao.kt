package id.shobrun.stikieventorganizer.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.shobrun.stikieventorganizer.models.entity.User
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.ID_USER
import id.shobrun.stikieventorganizer.room.AppDatabase.Companion.TABLE_USER

@Dao
interface UserDao {
    /**
     * CRUD Room
     */
    @Query("SELECT * FROM $TABLE_USER WHERE $ID_USER = :id")
    fun getDetailUser(id: Int): LiveData<User>

    @Query("SELECT * FROM $TABLE_USER WHERE USER_USERNAME = :username AND USER_PASSWORD = :password")
    fun getDetailUserByUsername(username: String, password: String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User): Int

    @Query("DELETE FROM $TABLE_USER WHERE ${ID_USER}= :id")
    fun delete(id: Int)

}