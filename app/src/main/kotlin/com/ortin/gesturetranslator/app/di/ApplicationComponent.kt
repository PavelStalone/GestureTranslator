package com.ortin.gesturetranslator.app.di

import android.app.Application
import com.ortin.gesturetranslator.data.di.DataModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        CoreModuleBind::class
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
