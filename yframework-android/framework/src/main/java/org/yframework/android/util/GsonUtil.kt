package org.yframework.android.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


/**
 * Description: GsonUtil<br>
 * Comments Name: GsonUtil<br>
 * Date: 2019-08-19 14:34<br>
 * Author: ysj<br>
 * EditorDate: 2019-08-19 14:34<br>
 * Editor: ysj
 */
object GsonUtil {
    private val gson = Gson()

    fun toJson(`object`: Any?): String {
        return gson.toJson(`object`)
    }

    fun toPrettyJson(`object`: Any?): String {
        return GsonBuilder().setPrettyPrinting().create().toJson(`object`)
    }

    fun <T> jsonToObject(JsonString: String, cls: Class<T>): T? {
        return gson.fromJson(JsonString, cls)
    }

    fun <T> jsonToList(JsonString: String): List<T>? {
        return gson.fromJson(JsonString, object : TypeToken<List<T>>() {}.type)
    }

    fun <T> jsonToListMap(JsonString: String): List<Map<String, T>>? {
        return gson.fromJson(JsonString, object : TypeToken<List<Map<String, T>>>() {}.type)
    }

    fun <T> jsonToMap(JsonString: String): Map<String, T>? {
        return gson.fromJson(JsonString, object : TypeToken<Map<String, T>>() {}.type)
    }
}