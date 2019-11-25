package org.yframework.android.view

import android.view.View

/**
 * Description: Presenter<br>
 * Comments Name: Presenter<br>
 * Date: 2019-08-20 17:57<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-20 17:57<br>
 * Editor: ysj
 */
interface Presenter : View.OnClickListener {
    override fun onClick(view: View?)
}