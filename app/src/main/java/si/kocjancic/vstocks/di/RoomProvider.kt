package si.kocjancic.vstocks.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import si.kocjancic.vstocks.room.AppDatabase
import si.kocjancic.vstocks.room.QuoteCacheDAO
import si.kocjancic.vstocks.room.UrlCacheDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomProvider {
    @Provides
    @Singleton
    fun provideRoomAppDB(@ApplicationContext context : Context) : AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"cache-db").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUrlCacheDAO(database: AppDatabase) : UrlCacheDAO{
        return database.urlCacheDAO()
    }
    @Provides
    @Singleton
    fun provideQuoteCacheDAO(database: AppDatabase) : QuoteCacheDAO{
        return database.quoteCacheDao()
    }

}