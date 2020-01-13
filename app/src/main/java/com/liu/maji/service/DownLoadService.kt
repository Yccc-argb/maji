package com.liu.maji.service

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.liu.maji.utils.StorageUtils
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownLoadService : IntentService("DownLoadService") {


    lateinit var notifyManager: NotificationManager
    lateinit var mBuilder: Notification.Builder
    override fun onHandleIntent(intent: Intent?) {
        notifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val appName = getString(applicationInfo.labelRes)
        val icon = applicationInfo.icon
        mBuilder = Notification.Builder(baseContext)
        mBuilder.setContentTitle(appName).setSmallIcon(icon)
        val downloadUrl = intent!!.getStringExtra("DOWNLOAD_URL")
        var inputStream: InputStream
        var out: FileOutputStream

        val url = URL(downloadUrl)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.run {
            requestMethod = "GET"
            doOutput = false
            connectTimeout = 15 * 1000
            readTimeout = 15 * 1000
            setRequestProperty("Connection", "Keep-Alive")
            setRequestProperty("Charset", "UTF-8")
            setRequestProperty("Accept-Encoding", "gzip, deflate")
            connect()
        }

        val byteTotal = httpURLConnection.contentLength.toLong()

        var byteSum: Long = 0
        var byteRead = 0
        inputStream = httpURLConnection.inputStream

        val file = StorageUtils.getCacheDirectory(this)
        val apkName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1,
                downloadUrl.length)
        val apkFile = File(file, apkName)
        out = FileOutputStream(apkFile)

        val buffer = ByteArray(1 * 1024)
        var oldPogress = 0

        while (true) {
            byteRead = inputStream.read(buffer)
            if (byteRead == -1){
                break
            }
            byteSum += byteRead
            out.write(buffer, 0, byteRead)
            val progress = (byteSum * 100 / byteTotal).toInt()
            if (progress != oldPogress){
                //更新进度
                updateProgress(progress)
            }
            oldPogress = progress
        }

        //安装apk
        installAPK(apkFile)
        notifyManager.cancel(0)
        out?.close()
        inputStream?.close()
    }

    fun updateProgress(progress:Int){
        mBuilder.setContentText("正在下载:$progress%").setProgress(100,progress,false)
        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(), PendingIntent.FLAG_CANCEL_CURRENT)
        mBuilder.setContentIntent(pendingIntent)
        notifyManager.notify(0,mBuilder.build())
    }


    fun installAPK(apkFile : File){
        var array : Array<String> = arrayOf("chmod","777",apkFile.toString())
        val processBuilder = ProcessBuilder(*array)
        processBuilder.start()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.fromFile(apkFile),"application/vnd.android.package-archive")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}