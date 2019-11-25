package org.yframework.android.view

import android.app.Activity
import androidx.databinding.ViewDataBinding
import org.yframework.android.util.WindowManagerUtil

/**
 * Description: PopupWindowFactory<br>
 * Comments Name: PopupWindowFactory<br>
 * Date: 2019-09-22 17:33<br>
 * Author: ysj<br>
 * EditorDate: 2019-09-22 17:33<br>
 * Editor: ysj
 */
class PopupWindowFactory private constructor() {

    companion object {

        private val POPUP_WINDOW_CACHES = LinkedHashMap<Int, BasicPopupWindow<*>>()

        fun <VB : ViewDataBinding> getPopupWindow(
            activity: Activity,
            layoutId: Int
        ): BasicPopupWindow<VB> {
            var popupWindow = POPUP_WINDOW_CACHES[layoutId]
            if (popupWindow != null) {
                return popupWindow as BasicPopupWindow<VB>
            }
            val displayMetrics = WindowManagerUtil
                .with(activity)
                .getDisplayMetrics()
            popupWindow = BasicPopupWindow.with<VB>(
                activity,
                layoutId
            )
                .width(displayMetrics.widthPixels)
                .height(displayMetrics.heightPixels)
                .build()
            POPUP_WINDOW_CACHES[layoutId] = popupWindow
            return popupWindow
        }
    }
}