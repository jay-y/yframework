package org.yframework.android.common


/**
 * Description: IRepository<br>
 * Comments Name: IRepository<br>
 * Date: 2019-08-23 10:56<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-23 10:56<br>
 * Editor: ysj
 */
interface IRepository<T> {
    fun list(t: T?): List<T>?

    fun size(t: T?): Int

    fun get(t: T): T?

    fun add(t: T): Int

    fun update(t: T): Int

    fun remove(t: T): Int
}