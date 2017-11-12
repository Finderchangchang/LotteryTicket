package gy.lotteryticket.base

import android.app.Application
import android.content.Context

import com.arialyy.frame.core.AbsFrame

/**
 * Created by Finder丶畅畅 on 2017/11/4 22:18
 * QQ群481606175
 */

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        AbsFrame.init(this)
    }

    companion object {
        var context: Context? = null
    }
}
