package org.yframework.android.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.becypress.framework.android.BuildConfig
import org.yframework.android.common.dispatchFailure
import org.yframework.android.common.tip


/**
 * Description: AbstractActivity<br>
 * Comments Name: AbstractActivity<br>
 * Date: 2019-08-14 11:23<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-14 11:23<br>
 * Editor: ysj
 */
abstract class AbstractActivity<VB : ViewDataBinding> : AppCompatActivity(),
    Presenter {

    lateinit var binding: VB

    abstract fun getLayoutId(): Int

    fun tipSucceed(msg: String?) {
        msg?.let { tip(it) }
    }

    fun tipFailed(error: Throwable) {
        dispatchFailure(error)
    }

    fun tipError(error: Throwable) {
        error.let {
            if (BuildConfig.DEBUG) {
                it.printStackTrace()
            }
            AlertDialog.Builder(this)
                .setMessage(it.message)
                .setPositiveButton("关闭") { _, _ -> finish() }
                .create()
                .show()
        }
    }

    fun setVariable(variableId: Int, @Nullable value: Any) {
        binding.setVariable(variableId, value)
    }

    override fun onClick(view: View?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.executePendingBindings()
        binding.lifecycleOwner = this
    }
}