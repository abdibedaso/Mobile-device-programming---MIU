package androidquiz.app.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidQuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            println("start application");
            androidContext(this@AndroidQuizApplication)
            modules(listOf(databaseModule, repositoryModule, viewModelModule))
        }
    }
}