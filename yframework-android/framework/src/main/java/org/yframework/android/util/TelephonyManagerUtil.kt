package org.yframework.android.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import java.util.*

/**
 * Description: TelephonyManagerUtil<br>
 * Comments Name: TelephonyManagerUtil<br>
 * Date: 2019-12-02 14:56<br>
 * Author: ysj<br>
 * EditorDate: 2019-12-02 14:56<br>
 * Editor: ysj
 */
class TelephonyManagerUtil private constructor(private val tm: TelephonyManager) {

    companion object {
        private var INSTANCE: TelephonyManagerUtil? = null

        fun with(activity: Activity): TelephonyManagerUtil {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    if (INSTANCE == null) {
                        INSTANCE = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            TelephonyManagerUtil(activity.getSystemService(TelephonyManager::class.java))
                        } else {
                            TelephonyManagerUtil(
                                activity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                            )
                        }
                    }
                }
            }
            return INSTANCE!!
        }
    }

    @SuppressLint("MissingPermission")
    fun getPhoneNumber(context: Context): String? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            tm.line1Number
        } else {
            null
        }
    }

    @SuppressLint("MissingPermission")
    fun getSimSerialNumber(context: Context): String? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            tm.simSerialNumber
        } else {
            null
        }
    }

    @SuppressLint("MissingPermission")
    fun getDeviceID(context: Context): String? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT < Build.VERSION_CODES.O
        ) {
            tm.deviceId
        } else {
            null
        }
    }

    @SuppressLint("MissingPermission")
    fun getImei(context: Context): String? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        ) {
            tm.imei
        } else {
            null
        }
    }

    @SuppressLint("MissingPermission")
    fun getMeid(context: Context): String? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        ) {
            tm.meid
        } else {
            null
        }
    }

    fun getUniquePseudoID(): String {
        val deviceInfo: String = "18" +
                Build.BOARD.length % 10 + Build.BRAND.length % 10 +

                Build.CPU_ABI.length % 10 + Build.DEVICE.length % 10 +

                Build.DISPLAY.length % 10 + Build.HOST.length % 10 +

                Build.ID.length % 10 + Build.MANUFACTURER.length % 10 +

                Build.MODEL.length % 10 + Build.PRODUCT.length % 10 +

                Build.TAGS.length % 10 + Build.TYPE.length % 10 +

                Build.USER.length % 10 // 13 位

        val serial = Build.SERIAL ?: "ANDROID"
        // API>=9 使用serial号
        return UUID(deviceInfo.hashCode().toLong(), serial.hashCode().toLong()).toString()
    }
}