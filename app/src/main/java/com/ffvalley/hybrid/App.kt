package com.ffvalley.hybrid

import android.app.Application
import kotlin.properties.Delegates

/**
 * @Description:
 * @Author: yjlove
 * @CreateDate: 2020/9/15
 */
class App : Application() {

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}