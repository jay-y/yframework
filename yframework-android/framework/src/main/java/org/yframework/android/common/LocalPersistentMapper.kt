package org.yframework.android.common

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


/**
 * Description: LocalPersistentMapper<br>
 * Comments Name: LocalPersistentMapper<br>
 * Date: 2019-08-21 09:11<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-21 09:11<br>
 * Editor: ysj
 */
interface LocalPersistentMapper<T> : IPersistentMapper<T> {

    @Delete
    override fun deleteByPrimaryKey(o: Any): Int

    @Delete
    override fun delete(t: T): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(t: T): Int

    @Insert
    override fun insertSelective(t: T): Int

    override fun selectAll(): List<T> {
        throw RuntimeException("Stub!")
    }

    override fun selectByPrimaryKey(o: Any): T {
        throw RuntimeException("Stub!")
    }

    override fun selectCount(t: T): Int {
        throw RuntimeException("Stub!")
    }

    override fun select(t: T): List<T> {
        throw RuntimeException("Stub!")
    }

    override fun selectOne(t: T): T {
        throw RuntimeException("Stub!")
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun updateByPrimaryKey(t: T): Int

    @Update
    override fun updateByPrimaryKeySelective(t: T): Int
}