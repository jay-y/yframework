package org.yframework.android.common

import androidx.lifecycle.ViewModel

/**
 * Description: AbstractViewModel<br>
 * Comments Name: AbstractViewModel<br>
 * Date: 2019-08-21 00:30<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-21 00:30<br>
 * Editor: ysj
 */
abstract class AbstractViewModel<ME : AbstractModelEntity, LPM : LocalPersistentMapper<ME>, RPM : RemotePersistentMapper<ME>, R : AbstractRepository<ME, LPM, RPM>>(
    private val entity: ME,
    private val repository: R
) : ViewModel()