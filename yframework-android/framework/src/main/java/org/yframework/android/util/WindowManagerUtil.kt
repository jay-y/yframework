package org.yframework.android.util

import android.app.Activity
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Description: DisplayMetricsUtil<br>
 * Comments Name: DisplayMetricsUtil<br>
 * Date: 2019-09-22 18:50<br>
 * Author: ysj<br>
 * EditorDate: 2019-09-22 18:50<br>
 * Editor: ysj
 */
class WindowManagerUtil private constructor(windowManager: WindowManager) {

    companion object {
        private var INSTANCE: WindowManagerUtil? = null

        fun with(activity: Activity): WindowManagerUtil {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    if (INSTANCE == null) {
                        INSTANCE =
                            WindowManagerUtil(activity.windowManager)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    private val displayMetrics = DisplayMetrics()

    private val rotation: Int

    init {
        val display = windowManager.defaultDisplay
        display.getMetrics(displayMetrics)
        rotation = display.rotation
    }

    fun getDisplayMetrics(): DisplayMetrics {
        return displayMetrics
    }
}