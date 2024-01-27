package com.okation.aivideocreator.di

import android.content.Context
import com.okation.aivideocreator.data.dao.BabyTrackerDao
import com.okation.aivideocreator.data.database.AppDatabase
import com.okation.aivideocreator.data.repository.DaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBabyTrackerDao(db: AppDatabase) = db.babyTrackerDao()

    @Singleton
    @Provides
    fun provideRepository(localDataSource: BabyTrackerDao) =
        DaoRepository(localDataSource)


}