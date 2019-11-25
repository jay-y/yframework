package com.custom.android.demo

import org.yframework.android.Application
import org.yframework.android.util.Log

/**
 * Description: AFRApplication<br>
 * Comments Name: AFRApplication<br>
 * Date: 2019-08-15 18:35<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-15 18:35<br>
 * Editor: ysj
 */
class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("程序初始化")
    }
}