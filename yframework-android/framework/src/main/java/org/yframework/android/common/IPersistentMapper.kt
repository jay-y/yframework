package org.yframework.android.common

/**
 * Description: IPersistentMapper<br>
 * Comments Name: IPersistentMapper<br>
 * Date: 2019-08-23 10:58<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-23 10:58<br>
 * Editor: ysj
 */
interface IPersistentMapper<T> {
    fun deleteByPrimaryKey(o: Any): Int

    fun delete(t: T): Int

    fun insert(t: T): Int

    fun insertSelective(t: T): Int

    fun selectAll(): List<T>

    fun selectByPrimaryKey(o: Any): T

    fun selectCount(t: T): Int

    fun select(t: T): List<T>

    fun selectOne(t: T): T

    fun updateByPrimaryKey(t: T): Int

    fun updateByPrimaryKeySelective(t: T): Int
}