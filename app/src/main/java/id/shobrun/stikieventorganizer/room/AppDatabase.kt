package id.shobrun.stikieventorganizer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.shobrun.stikieventorganizer.models.entity.*
import id.shobrun.stikieventorganizer.utils.DateConverter

@Database(entities = [Event::class, Invitation::class, Participant::class, User::class],version = 7, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun eventDao() : EventDao
    abstract fun invitationDao() : InvitationDao
    abstract fun participantDao() : ParticipantDao
    abstract fun userDao() : UserDao

    companion object{
        private const val DB_SEO = "SEO-db"

        const val TABLE_INVITATION = "INVITATION_table"
        const val ID_INVITATION = "invitation_id"

        const val TABLE_EVENT = "event_table"
        const val ID_EVENT = "event_id"

        const val TABLE_PARTICIPANT = "participant_table"
        const val ID_PARTICIPANT = "participant_id"

        const val TABLE_USER = "user_table"
        const val ID_USER = "user_id"

        const val TABLE_PARTICIPANT_INVITATION = "participant_invitation_table"
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        ).also { instance = it }
                }
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java,
                DB_SEO
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}