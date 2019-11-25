package org.yframework.android.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.yframework.android.common.dispatchFailure
import org.yframework.android.common.tip


/**
 * Description: AbstractFragment<br>
 * Comments Name: AbstractFragment<br>
 * Date: 2019-08-19 15:39<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-19 15:39<br>
 * Editor: ysj
 */
abstract class AbstractFragment<VB : ViewDataBinding> : Fragment(),
    Presenter {

    lateinit var binding: VB

    lateinit var parentActivity: Activity

    abstract fun getLayoutId(): Int

    fun tipSucceed(msg: String?) {
        msg?.let { parentActivity.tip(it) }
    }

    fun tipFailed(error: Throwable) {
        parentActivity.dispatchFailure(error)
    }

    fun setVariable(variableId: Int, @Nullable value: Any) {
        binding.setVariable(variableId, value)
    }

    override fun onClick(view: View?) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
        parentActivity = activity ?: throw NullPointerException("父级Activity不能为空")
    }
}