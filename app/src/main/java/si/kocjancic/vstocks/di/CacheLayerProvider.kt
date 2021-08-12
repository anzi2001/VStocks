package si.kocjancic.vstocks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import si.kocjancic.vstocks.api.IEXApi
import si.kocjancic.vstocks.cache.QuoteCacheLayer
import si.kocjancic.vstocks.room.QuoteCacheDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheLayerProvider {
    @Provides
    @Singleton
    fun provideQuoteCacheLayer(iexApi: IEXApi, quoteCacheDAO: QuoteCacheDAO) : QuoteCacheLayer{
        return QuoteCacheLayer(iexApi,quoteCacheDAO)
    }
}