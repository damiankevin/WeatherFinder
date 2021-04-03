package id.com.android.weatherfinder.repository

import android.app.Application
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import id.com.android.weatherfinder.model.ModelDB
import id.com.android.weatherfinder.tools.UtilConstant


@Database(entities = [(ModelDB::class)], version = UtilConstant.DATA_CONTENT_VERSION)
@TypeConverters()
abstract class RepositoryContent : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    @Dao
    interface ContentDao {
        @Query("SELECT id FROM location where hasFavourite LIKE :favourite")
        fun allContentIdFavourite(favourite: Boolean?): List<Int>

        @Query("DELETE FROM location WHERE id = :id")
        fun deleteFavourite(id: Int?)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addContent(content: ModelDB): Long
    }

    class ContentConverterFavourite(var content: ModelDB) {
        fun toContent(): ModelDB? {
            val contentDetail = content
            contentDetail.hasFavourite = true
            return contentDetail
        }
    }


    companion object {
        fun newInstance(application: Application): RepositoryContent {
            return Room.databaseBuilder(application, RepositoryContent::class.java, UtilConstant.PARAM_DATABASE)
                .addMigrations(MIGRATION_1_2)
                .build()
        }
        internal val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
             }
        }
    }


}