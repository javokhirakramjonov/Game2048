package uz.gita.game2048.app

import android.app.Application
import uz.gita.game2048.data.local.MySharePref
import uz.gita.game2048.data.repository.impl.AppRepositoryImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharePref.init(this)
        AppRepositoryImpl.init()
    }
}