package com.custom.android.demo.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.custom.android.demo.BR
import com.custom.android.demo.R
import com.custom.android.demo.databinding.ActivityDebugBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.yframework.android.common.navigateUpToActivity
import org.yframework.android.util.Log
import org.yframework.android.view.AbstractActivity


/**
 * Description: DebugActivity<br>
 * Comments Name: DebugActivity<br>
 * Date: 2019-08-24 10:05<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-24 10:05<br>
 * Editor: ysj
 */
class DebugActivity : AbstractActivity<ActivityDebugBinding>() {

    private lateinit var testBitmap: Bitmap

    override fun getLayoutId(): Int = R.layout.activity_debug

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.presenter, this)

//        testBitmap = BitmapFactory.decodeResource(resources, R.drawable.me)
//        displayImage(testBitmap)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ad_action_add_personnel -> {
                Log.d("开始新增")
            }
            R.id.ad_action_get_personnel -> {
                Log.d("开始查询")
            }
            R.id.ad_action_remove_personnel -> {
                Log.d("开始清空")
            }
            R.id.ad_action_to_main_activity -> goMainActivity()
        }
    }

    private fun displayImage(bitmap: Bitmap) {
        runOnUiThread {
            binding.adImageView.setImageBitmap(bitmap)
        }
    }

    private fun goMainActivity() {
        GlobalScope.launch {
            navigateUpToActivity(MainActivity::class.java, null, false)
        }
    }
}