package org.yframework.android.view

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Description: BasicPopupWindow<br>
 * Comments Name: BasicPopupWindow<br>
 * Date: 2019-09-22 17:33<br>
 * Author: ysj<br>
 * EditorDate: 2019-09-22 17:33<br>
 * Editor: ysj
 */
class BasicPopupWindow<VB : ViewDataBinding>(builder: Builder<VB>) :
    PopupWindow(
        null,
        builder.width,
        builder.height,
        builder.isFocusable
    ) {

    companion object {
        fun <VB : ViewDataBinding> with(activity: Activity, layoutId: Int): Builder<VB> {
            return Builder(activity, layoutId)
        }
    }

    val binding: VB

    init {
        binding = DataBindingUtil.inflate(
            builder.activity.layoutInflater,
            builder.layoutId,
            null as ViewGroup?,
            false
        ) as VB
        contentView = binding.root
        isOutsideTouchable = builder.isOutsideTouchable
        setBackgroundDrawable(BitmapDrawable(builder.activity.resources, null as Bitmap?))
        binding.executePendingBindings()
        if (builder.touchDismiss) contentView.setOnClickListener { dismiss() }
    }

    fun show(parent: View?) {
        parent?.let {
            if (!this.isShowing) {
                this.showAtLocation(parent, Gravity.CENTER, 0, 0)
            }
        }
    }

    fun setVariable(variableId: Int, @Nullable value: Any) {
        binding.setVariable(variableId, value)
    }

    class Builder<VB : ViewDataBinding>(val activity: Activity, val layoutId: Int) {

        var width = 0

        var height = 0

        var isFocusable: Boolean = true

        var isOutsideTouchable: Boolean = true

        var touchDismiss: Boolean = true

        fun width(width: Int): Builder<VB> {
            this.width = width
            return this
        }

        fun height(height: Int): Builder<VB> {
            this.height = height
            return this
        }

        fun unfocusable(): Builder<VB> {
            this.isFocusable = false
            return this
        }

        fun outsideUntouchable(): Builder<VB> {
            this.isOutsideTouchable = false
            return this
        }

        fun touchMiss(): Builder<VB> {
            this.touchDismiss = false
            return this
        }

        fun build(): BasicPopupWindow<VB> {
            return BasicPopupWindow(this)
        }
    }
}