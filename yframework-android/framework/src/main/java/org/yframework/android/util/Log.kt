package org.yframework.android.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object Log {

    private const val TAG = "www.becypress.com"

    private const val TOP_BORDER =
        "══════════════════════════════════════════════════════════════════════════════════════════════════════════"
    
    private const val BOTTOM_BORDER =
        "══════════════════════════════════════════════════════════════════════════════════════════════════════════"

    private const val CHUNK_SIZE = 106 //设置字节数

    private var debug: Boolean = true//是否打印log
    private var savesd: Boolean = false//是否存log到sd卡
    private var logDir = ""//设置文件存储目录
    private var logSize = 2 * 1024 * 1024L//设置log文件大小 k
    private val executorService: ExecutorService = Executors.newFixedThreadPool(1)

    init {
        initLogFile()
    }

    fun v(msg: String) = v(TAG, msg)

    fun d(msg: String) = d(TAG, msg)

    fun i(msg: String) = i(TAG, msg)

    fun w(msg: String) = w(TAG, msg)

    fun e(msg: String) = e(TAG, msg)

    fun e(msg: String, ex: Throwable) =
        e(TAG, msg, ex)

    fun v(tag: String? = TAG, msg: String) = debug.print(tag, msg, android.util.Log.VERBOSE)

    fun d(tag: String? = TAG, msg: String) = debug.print(tag, msg, android.util.Log.DEBUG)

    fun i(tag: String? = TAG, msg: String) = debug.print(tag, msg, android.util.Log.INFO)

    fun w(tag: String? = TAG, msg: String) = debug.print(tag, msg, android.util.Log.WARN)

    fun e(tag: String? = TAG, msg: String) = debug.print(tag, msg, android.util.Log.ERROR)

    fun e(tag: String? = TAG, msg: String, ex: Throwable) = debug.print(tag, msg, android.util.Log.ERROR, ex)

    private fun targetStackTraceMSG(): String {
        val targetStackTraceElement = getTargetStackTraceElement()
        return if (targetStackTraceElement != null) {
            "at ${targetStackTraceElement.className}.${targetStackTraceElement.methodName}(${targetStackTraceElement.fileName}:${targetStackTraceElement.lineNumber})"
        } else {
            ""
        }
    }

    private fun getTargetStackTraceElement(): StackTraceElement? {
        var targetStackTrace: StackTraceElement? = null
        var shouldTrace = false
        val stackTrace = Thread.currentThread().stackTrace
        for (stackTraceElement in stackTrace) {
            val isLogMethod = stackTraceElement.className == Log::class.java.name
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement
                break
            }
            shouldTrace = isLogMethod
        }
        return targetStackTrace
    }

    private fun initLogFile() {
//        logDir = "${FileUtils.getRootDir()}/becypress.com"
//        FileUtils.mkDir(logDir)
    }

    private fun Boolean.print(tag: String?, msg: String, type: Int, ex: Throwable? = null) {
        if (!this) {
            return
        }
        val newMsg = msgFormat(msg)

//        savesd.saveToSd(
//            "${SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss",
//                Locale.US
//            ).format(Date())}\n${targetStackTraceMSg()}", msg
//        )
        when (type) {
            android.util.Log.VERBOSE -> android.util.Log.v(tag, newMsg)
            android.util.Log.DEBUG -> android.util.Log.d(tag, newMsg)
            android.util.Log.INFO -> android.util.Log.i(tag, newMsg)
            android.util.Log.WARN -> android.util.Log.w(tag, newMsg)
            android.util.Log.ERROR -> {
                if (ex != null) {
                    android.util.Log.e(tag, newMsg)
                } else {
                    android.util.Log.e(tag, newMsg, ex)
                }
            }
        }

    }

    private fun msgFormat(msg: String): String {
        val bytes: ByteArray = msg.toByteArray()
        val length = bytes.size
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        var newMsg = "$TOP_BORDER\n\t${sdf.format(Date())}\n\t${targetStackTraceMSG()}"
        if (length > CHUNK_SIZE) {
            var i = 0
            while (i < length) {
                val count = Math.min(length - i, CHUNK_SIZE)
                val tempStr = String(bytes, i, count)
                newMsg += "\n\t$tempStr"
                i += CHUNK_SIZE
            }
        } else {
            newMsg += "\n\t$msg"
        }
        newMsg += "\n$BOTTOM_BORDER"
        return newMsg

    }

//    private fun Boolean.saveToSd(tag: String, msg: String) {
//        if (!this) {
//            return
//        }
//        executorService.submit({
            //            val data = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
//            var files = FileUtils.sortByTime(File(logDir))?.filter { it -> it.name.contains(data) }
//            var filepath: String
//            if (files != null && files.isNotEmpty()) {
//                val length: Long = FileUtils.getLeng(files[0])
//                if (length > logSize) {
//                    val id = files[0].name.replace("${data}_", "").replace(".log", "").toInt() + 1
//                    filepath = "$logDir/${data}_$id.log"
//                    FileUtils.creatFile(filepath)
//                } else {
//                    filepath = files[0].absolutePath
//                }
//            } else {
//                filepath = "$logDir/${data}_1.log"
//                FileUtils.creatFile(filepath)
//            }
//            FileUtils.appendText(File(filepath), "\r\n$tag\n$msg")
//        })
//
//    }


    /**
     * 是否打印log输出
     * @param debug
     */
    fun debug(debug: Boolean): Log {
        Log.debug = debug
        return this
    }

    /**
     * 是否保存到sd卡
     * @param savesd
     */
    fun saveSd(savesd: Boolean): Log {
        Log.savesd = savesd
        return this
    }

    /**
     * 设置每个log的文件大小
     * @param logSize 文件大小 byte
     */
    fun logSize(logSize: Long): Log {
        Log.logSize = logSize
        return this

    }

    /**
     * 设置log文件目录
     * @param logDir 文件目录
     */
    fun logDir(logDir: String): Log {
        Log.logDir = logDir
        return this
    }
}