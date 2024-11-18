package com.welldone.composeapptemplate.logs

import android.util.Log
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FileLoggingTree(val logDir: File) : Timber.Tree() {

    private var logFile: File

    init {
        if (!logDir.exists()) {
            logDir.mkdirs()
        }

        val dateStr = getFormatedTime(System.currentTimeMillis(), format = "yyyy-MM-dd")
        logFile = File(logDir, "hddac-dcs-log-$dateStr.txt")
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        try {
            FileWriter(logFile, true).use { writer ->
                val time = getFormatedTime(
                    System.currentTimeMillis(), format = "yyyy-MM-dd HH:mm:ss.SSS"
                )
                writer.write(String.format("[%s] %s: %s\n", time, tag, message))
                if (t != null) {
                    writer.write(Log.getStackTraceString(t))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getFormatedTime(timestamp: Long, format: String = "yyyy-MM-dd HH:mm:ss"): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        val formattedTime = sdf.format(Date(timestamp))
        return formattedTime
    }
}
