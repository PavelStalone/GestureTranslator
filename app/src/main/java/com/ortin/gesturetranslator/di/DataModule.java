package com.ortin.gesturetranslator.di;

import android.content.Context;

import com.ortin.gesturetranslator.data.repository.SettingsRepositoryImpl;
import com.ortin.gesturetranslator.data.storage.SettingsStorage;
import com.ortin.gesturetranslator.data.storage.SharedPrefSettingsStorage;
import com.ortin.gesturetranslator.domain.repository.SettingsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {
    @Provides
    @Singleton
    SettingsRepository provideSettingsRepository(SettingsStorage settingsStorage) {
        return new SettingsRepositoryImpl(settingsStorage);
    }

    @Provides
    @Singleton
    SettingsStorage provideSettingsStorage(@ApplicationContext Context context) {
        return new SharedPrefSettingsStorage(context);
    }
}
