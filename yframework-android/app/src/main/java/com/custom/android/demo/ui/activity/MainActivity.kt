package com.custom.android.demo.ui.activity

import android.os.Bundle
import com.custom.android.demo.R
import com.custom.android.demo.databinding.ActivityMainBinding
import org.yframework.android.view.AbstractActivity


/**
 * Description: MainActivity<br>
 * Comments Name: MainActivity<br>
 * Date: 2019-08-18 22:52<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-18 22:52<br>
 * Editor: ysj
 */
class MainActivity : AbstractActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}