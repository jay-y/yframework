package org.yframework.android

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import java.util.*


/**
 * Description: Application<br>
 * Comments Name: Application<br>
 * Date: 2019-08-15 18:37<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-15 18:37<br>
 * Editor: ysj
 */
abstract class Application : Application() {

    companion object {
        fun isDebug(context: Context): Boolean {
            return try {
                val info = context.applicationInfo
                info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
            } catch (e: Exception) {
                false
            }

        }
    }

    /**
     * 堆栈存储activity
     * 注:建议只存储launchMode="singleInstance"的Activity
     */
    private val activityStack: Stack<Activity> = Stack()

    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
            // do nothings
        }

        override fun onActivityResumed(activity: Activity?) {
            // do nothings
        }

        override fun onActivityStarted(activity: Activity?) {
            // do nothings
        }

        override fun onActivityDestroyed(activity: Activity?) {
            activity?.let {
                finishActivity(it)
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            // do nothings
        }

        override fun onActivityStopped(activity: Activity?) {
            // do nothings
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            activity?.let {
                addActivity(it)
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
//        LitePal.initialize(this)
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity
     */
    fun addActivity(activity: Activity) {
        if (activityStack.search(activity) == -1) {
            activityStack.add(activity)
        }
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activity.finish()
            activityStack.remove(activity)
        }
    }

    /**
     * 结束所有堆栈中的Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack.size
        while (i < size) {
            if (null != activityStack[i]) {
                activityStack[i].finish()
            }
            i++
        }
        activityStack.clear()
    }
}