package org.yframework.android.util.permission

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.yframework.android.util.Log
import java.lang.reflect.Method
import java.util.*

/**
 * Description: PermissionUtil<br>
 * Comments Name: PermissionUtil<br>
 * Date: 2019-08-19 13:55<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-19 13:55<br>
 * Editor: ysj
 */
class PermissionUtil private constructor(private val obj: Any) {

    private var permissions: Array<String>? = null
    private var requestCode: Int = 0

    init {
        this.requestCode =
            PERMISSIONS_REQUEST_CODE
    }

    fun permissions(permissions: String): PermissionUtil {
        this.permissions = arrayOf(permissions)
        return this
    }

    fun permissions(permissions: Array<String>): PermissionUtil {
        this.permissions = permissions
        return this
    }

    fun permissions(permissionList: List<String>): PermissionUtil {
        this.permissions = permissionList.toTypedArray()
        return this
    }

    fun requestCode(requestCode: Int): PermissionUtil {
        this.requestCode = requestCode
        return this
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    fun request() {
        requestPermissions(
            obj,
            requestCode,
            permissions
        )
    }

    companion object {
        const val PERMISSIONS_REQUEST_CODE = 1024

        // 定位需要权限
        val PERMISSIONS_GROUP_LOACATION =
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

        // 通讯录操作权限
        val PERMISSIONS_GROUP_CONTCATS = arrayOf(
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
        )

        // 摄像机需要权限
        val PERMISSIONS_GROUP_CAMERA = arrayOf(
            Manifest.permission.CAMERA,
            "android.hardware.camera",
            "android.hardware.camera.autofocus"
        )

        // 录音需要权限
        val PERMISSIONS_GROUP_RECORD_AUDIO = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
        )

        /**
         * checkLocation:(检查定位服务权限). <br></br>
         *
         * @param context
         * @return
         * @author ysj
         * @since JDK 1.7
         * date: 2016-3-25 下午4:22:26 <br></br>
         */
        fun checkLocation(context: Any?): Boolean {
            return check(
                context,
                PERMISSIONS_GROUP_LOACATION
            )
        }

        /**
         * checkRecoreAudio:(检查录音权限). <br></br>
         *
         * @param context
         * @return
         * @author ysj
         * @since JDK 1.7
         * date: 2016-3-25 下午4:29:32 <br></br>
         */
        fun checkRecoreAudio(context: Any?): Boolean {
            return check(
                context,
                PERMISSIONS_GROUP_RECORD_AUDIO
            )
        }

        /**
         * checkCamera:(检查摄像机权限). <br></br>
         *
         * @param context
         * @return
         * @author ysj
         * @since JDK 1.7
         * date: 2016-3-25 下午4:22:26 <br></br>
         */
        fun checkCamera(context: Any?): Boolean {
            return check(
                context,
                PERMISSIONS_GROUP_CAMERA
            )
        }

        /**
         * checkContacts:(检查通讯录操作权限). <br></br>
         *
         * @param context
         * @return
         * @author ysj
         * @since JDK 1.7
         * date: 2016-8-24 下午4:22:26 <br></br>
         */
        fun checkContacts(context: Any): Boolean {
            return check(
                context,
                PERMISSIONS_GROUP_CONTCATS
            )
        }

        fun check(context: Any, value: String): Boolean {
            var check = PackageManager.PERMISSION_DENIED
            if (context is Activity) {
                check = ContextCompat.checkSelfPermission(context, value)
            } else if (context is Fragment) {
                context.activity?.let {
                    check = ContextCompat.checkSelfPermission(it, value)
                }
            }
            if (check == PackageManager.PERMISSION_DENIED) {
                return false
            }
            return true
        }

        fun check(context: Any?, value: Array<String>): Boolean {
            try {
                if (null == context)
                    throw RuntimeException("Context is null.")
                var checked = true
                for (i in value.indices) {
                    if (!check(
                            context,
                            value[i]
                        )
                    ) {
                        checked = false
                    }
                }
                return checked
            } catch (e: Exception) {
                e.message?.let { Log.e(it, e) }
                return false
            }
        }

        fun check(context: Any?, value: ArrayList<String>): Boolean {
            return check(
                context,
                value.toTypedArray()
            )
        }

        fun with(context: Any): PermissionUtil {
            return PermissionUtil(context)
        }

        fun needPermission(obj: Any, requestCode: Int, permissions: Array<String>) {
            requestPermissions(
                obj,
                requestCode,
                permissions
            )
        }

        fun needPermission(obj: Any, requestCode: Int, permission: String) {
            needPermission(
                obj,
                requestCode,
                arrayOf(permission)
            )
        }

        /**
         * 请求权限结果
         *
         * @param obj
         * @param requestCode
         * @param permissions
         */
        fun onRequestPermissionsResult(obj: Any, requestCode: Int, permissions: Array<String>?) {
            if (check(
                    getContext(
                        obj
                    ), permissions!!
                )
            ) {
                onSuccess(
                    obj,
                    requestCode
                )
            } else {
                onError(
                    obj,
                    requestCode
                )
            }
        }

        fun permissionsToList(vararg permission: String): List<String> {
            val permissionList = ArrayList<String>()
            for (value in permission) {
                permissionList.add(value)
            }
            return permissionList
        }

        private fun <A : Annotation> findMethodWithRequestCode(
            clazz: Class<*>,
            annotation: Class<A>, requestCode: Int
        ): Method? {
            for (method in clazz.declaredMethods) {
                if (method.isAnnotationPresent(annotation)) {
                    if (isEqualRequestCodeFromAnntation(
                            method,
                            annotation,
                            requestCode
                        )
                    ) {
                        return method
                    }
                }
            }
            return null
        }

        private fun isEqualRequestCodeFromAnntation(
            m: Method,
            clazz: Class<*>,
            requestCode: Int
        ): Boolean {
            return when (clazz) {
                PermissionError::class.java -> requestCode == m.getAnnotation(
                    PermissionError::class.java).value
                PermissionSuccess::class.java -> requestCode == m.getAnnotation(
                    PermissionSuccess::class.java).value
                else -> false
            }
        }

        private fun getContext(context: Any): Context? {
            if (context is Activity) {
                return context
            } else if (context is Fragment) {
                return context.activity
            }
            return null
        }

        private fun requestPermissions(obj: Any, requestCode: Int, permissions: Array<String>?) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissions!!.isNotEmpty()) {
                    when (obj) {
                        is Activity -> ActivityCompat.requestPermissions(
                            obj,
                            permissions,
                            requestCode
                        )
                        is Fragment -> obj.requestPermissions(permissions, requestCode)
                        else -> throw IllegalArgumentException(obj.javaClass.name + " is not supported")
                    }
                }
            } else {
                onRequestPermissionsResult(
                    obj,
                    requestCode,
                    permissions
                )
            }
        }

        private fun onSuccess(obj: Any, requestCode: Int) {
            val method =
                findMethodWithRequestCode(
                    obj.javaClass,
                    PermissionSuccess::class.java,
                    requestCode
                )

            executeMethod(
                obj,
                method
            )
        }

        private fun onError(obj: Any, requestCode: Int) {
            val method =
                findMethodWithRequestCode(
                    obj.javaClass,
                    PermissionError::class.java, requestCode
                )

            executeMethod(
                obj,
                method
            )
        }

        private fun executeMethod(obj: Any, method: Method?): Method? {
            try {
                if (method != null) {
                    if (!method.isAccessible) method.isAccessible = true
                    method.invoke(obj)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                return method
            }

        }
    }
}