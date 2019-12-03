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
class HardwareInformation(
    val bid: String = Build.ID, // 系统当前开发版本

    val display: String = Build.DISPLAY, // 设备显示版本

    val serial: String = Build.SERIAL, // 串口序列号

    val fingerprint: String = Build.FINGERPRINT, // 设备指纹

    val host: String = Build.HOST, // 主机地址

    val productBrand: String = Build.BRAND, // 产品出厂商品牌

    val productName: String = Build.PRODUCT, // 产品名称

    val productBoardName: String = Build.BOARD, // 产品主板名字

    val productDeviceName: String = Build.DEVICE, // 产品驱动名

    val productManufacturer: String = Build.MANUFACTURER, // 产品制造商

    val sdkVersion: Int = Build.VERSION.SDK_INT, // 当前手机SDK版本号

    val systemVersion: String = Build.VERSION.RELEASE, // 手机系统版本号

    val systemBootloaderVersion: String = Build.BOOTLOADER // 系统引导程序版本号
) : Serializable