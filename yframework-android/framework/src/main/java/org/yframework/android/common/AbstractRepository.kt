package org.yframework.android.common

/**
 * Description: AbstractRepository<br>
 * Comments Name: AbstractRepository<br>
 * Date: 2019-08-21 09:12<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-21 09:12<br>
 * Editor: ysj
 */
abstract class AbstractRepository<ME : AbstractModelEntity, LPM : LocalPersistentMapper<ME>, RPM : RemotePersistentMapper<ME>>(
    private val localPersistentMapper: LPM,
    private var remotePersistentMapper: RPM
) : IRepository<ME>