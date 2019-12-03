package com.custom.android.demo.ui.activity

import android.os.Bundle
import com.custom.android.demo.R
import com.custom.android.demo.databinding.ActivitySplashBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.yframework.android.Application
import org.yframework.android.common.navigateUpToActivity
import org.yframework.android.view.AbstractActivity

/**
 * Description: 启动页<br>
 * Comments Name: SplashActivity<br>
 * Date: 2019-08-14 09:55<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-14 09:55<br>
 * Editor: ysj
 */
class SplashActivity : AbstractActivity<ActivitySplashBinding>() {

    private var hasPermissions: Boolean = false

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        hasPermissions = faceDetectorPermissionDelegate.hasPermissions()
//
//        if (!hasPermissions) {
//            faceDetectorPermissionDelegate.requestPermissions()
//        }
        init()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (faceDetectorPermissionDelegate.resultGranted(requestCode, permissions, grantResults)) {
//            init()
//        }
    }

    private fun init() {
//        Megvii.INSTANCE.init(this)
        if (!goDebugActivity()) {
            goMainActivity()
        }
    }

    private fun goMainActivity() {
        GlobalScope.launch {
            delay(3000)
            navigateUpToActivity(MainActivity::class.java)
        }
    }

    private fun goDebugActivity(): Boolean {
        if (Application.isDebug(applicationContext)) {
            GlobalScope.launch {
                navigateUpToActivity(DebugActivity::class.java)
            }
            return true
        }
        return false
    }
}