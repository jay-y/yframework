package org.yframework.android.common

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.becypress.framework.android.BuildConfig
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Description: CommonExtens<br>
 * Comments Name: CommonExtens<br>
 * Date: 2019-08-20 22:09<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-20 22:09<br>
 * Editor: ysj
 */

enum class TipTypeEnum {
    NORMAL,
    SUCCESS,
    WARNING,
    ERROR
}

fun Activity.tip(
    msg: CharSequence,
    type: TipTypeEnum = TipTypeEnum.NORMAL,
    duration: Int = Toast.LENGTH_SHORT
) {
    runOnUiThread {
        when (type) {
            TipTypeEnum.NORMAL -> Toast.makeText(this, msg, duration).show()
            TipTypeEnum.SUCCESS -> Toast.makeText(this, msg, duration).show()
            TipTypeEnum.WARNING -> Toast.makeText(this, msg, duration).show()
            TipTypeEnum.ERROR -> Toast.makeText(this, msg, duration).show()
        }
    }
}

fun Activity.dispatchFailure(error: Throwable?) {
    error?.let {
        if (BuildConfig.DEBUG) {
            it.printStackTrace()
        }
        when {
            it is NullPointerException -> {
            }
            error is SocketTimeoutException -> it.message?.let { tip("网络连接超时",
                TipTypeEnum.ERROR
            ) }
            it is UnknownHostException || it is ConnectException -> //网络未连接
                it.message?.let { tip("网络未连接", TipTypeEnum.ERROR) }
            else -> it.message?.let { message -> tip(message,
                TipTypeEnum.ERROR
            ) }
        }
    }
}

fun Activity.navigateUpToActivity(c: Class<*>, bundle: Bundle? = null, killed: Boolean? = true) {
    val intent = Intent(this, c)
    bundle?.let { intent.putExtras(it) }
    startActivity(intent)
    if (killed!!) finish()
}