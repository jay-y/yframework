package org.yframework.android.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.yframework.android.util.Log
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * Description: FragmentFactory<br>
 * Comments Name: FragmentFactory<br>
 * Date: 2019-08-19 16:08<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-19 16:08<br>
 * Editor: ysj
 */
class FragmentFactory {

    private val registry = HashMap<Int, Fragment>() //Fragment注册集合

    private val registryIds = HashMap<Int, Int>() //注册ID

    private var count: AtomicInteger = AtomicInteger(0) //记录条数

    fun getFragment(code: Int): Fragment? {
        if (null == registry[code]) {
            throw RuntimeException("Please register the Fragment first")
        }
        return registry[code]
    }

    /**
     * 获取注册时使用ID
     *
     * @param index
     * @return
     */
    fun getId(index: Int): Int? {
        return registryIds[index]
    }

    /**
     * 获取组件注册总数
     *
     * @return
     */
    fun getCount(): Int? {
        return count.get()
    }

    fun registerFragment(code: Int, container: Int, fragment: Any, fragmentManager: FragmentManager? = null) {
        if (fragmentManager != null && fragment in fragmentManager.fragments && registryIds.containsValue(code)) {
            return
        }
        if (registryIds.containsValue(code)) {
            fragmentManager?.beginTransaction()
                ?.replace(container, registry[code]!!)
                ?.commit()
        } else {
            registryIds[count.get()] = code
            if (fragment is Fragment) {
                registry[code] = fragment
                count.addAndGet(1)
                fragmentManager?.beginTransaction()
                    ?.replace(container, fragment)
                    ?.commit()
            }
        }
        Log.d("Fragment注册器统计：$count")
    }

    fun releaseFragment(fragmentManager: FragmentManager? = null) {
        for (index in 0 until count.get()) {
            val code = registryIds[index]
            fragmentManager?.beginTransaction()?.remove(registry[code]!!)
            registry.remove(code)
            registryIds.remove(index)
        }
        count.set(0)
    }

    companion object {

        private var instance: FragmentFactory =
            FragmentFactory()

        /**
         * 获取实例 <br></br>
         *
         * @return Fragment
         * @author ysj
         * date: 2015-6-24 下午5:48:12 <br></br>
         */
        fun getInstance(): FragmentFactory {
            return instance
        }

        /**
         * 创建新实例
         *
         * @return this
         */
        fun newInstance(): FragmentFactory {
            return FragmentFactory()
        }
    }
}