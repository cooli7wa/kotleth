package com.ehanoc.deth

import android.app.Application
import android.content.SharedPreferences
import com.ehanoc.deth.dagger.components.AppComponent
import com.ehanoc.deth.dagger.components.DaggerAppComponent
import com.ehanoc.deth.dagger.components.Web3Component
import com.ehanoc.deth.dagger.modules.AppModule
import com.ehanoc.deth.dagger.modules.Web3Module
import javax.inject.Inject

/**
 * Created by bruno.
 */
class App : Application() {

    @Inject
    lateinit var prefs: SharedPreferences

    private val mApplicationComponent by lazy {
         DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private val _web3Component by lazy {
        mApplicationComponent.plus(Web3Module(this))
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent.inject(this)
    }

    fun getApplicationComponent(): AppComponent {
        return mApplicationComponent
    }

    fun getWeb3Component(): Web3Component {
        return _web3Component
    }
}