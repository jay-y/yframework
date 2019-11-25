package org.yframework.android.hardware

import android.os.Build
import java.io.Serializable

/**
 * Description: HardwareInformation<br>
 * Comments Name: HardwareInformation<br>
 * Date: 2019-09-20 17:31<br>
 * Author: ysj<br>
 * EditorDate: 2019-09-20 17:31<br>
 * Editor: ysj
 */
class HardwareInformation : Serializable {
    val id: String = Build.ID // 获取系统当前开发版本

    val display: String = Build.DISPLAY // 获取设备显示版本

    val serial: String = Build.SERIAL // 获取串口序列号

    val fingerprint: String = Build.FINGERPRINT // 获取设备唯一标识符

    val host: String = Build.HOST // 获取主机地址

    val productBrand: String = Build.BRAND // 获取产品出厂商品牌

    val productName: String = Build.PRODUCT // 获取产品名称

    val productBoardName: String = Build.BOARD // 获取产品主板名字

    val productDeviceName: String = Build.DEVICE // 获取产品驱动名

    val productManufacturer: String = Build.MANUFACTURER // 获取产品制造商

    val sdkVersion: Int = Build.VERSION.SDK_INT // 获取当前手机SDK版本号

    val systemVersion: String = Build.VERSION.RELEASE // 获取手机系统版本号

    val systemBootloaderVersion: String = Build.BOOTLOADER // 系统引导程序版本号
}