package com.gdeer.gdtesthub.deviceFile

import android.app.ListActivity
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.text.format.Formatter
import android.util.Log
import android.widget.ArrayAdapter
import java.io.File

class SysDirActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = listOf(
                "Environment.getDataDirectory().absolutePath\n${Environment.getDataDirectory().absolutePath}",
                "Environment.getRootDirectory().absolutePath\n${Environment.getRootDirectory().absolutePath}",
                "Environment.getDownloadCacheDirectory().absolutePath\n${Environment.getDownloadCacheDirectory().absolutePath}",
                "Environment.getExternalStorageDirectory().absolutePath\n${Environment.getExternalStorageDirectory().absolutePath}",
                "context.getCacheDir()\n$cacheDir",
                "context.getFilesDir()\n$filesDir",
                "context.getExternalCacheDir()\n$externalCacheDir",
                "context.getExternalFilesDir(null)\n${getExternalFilesDir(null)}"
        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listAdapter = arrayAdapter

        val f = File(externalCacheDir.absolutePath + "/a.txt")
        f.createNewFile()
        Log.d("zhangjl", f.absolutePath + " " + f.exists())
        showAvailableSize()
    }

    /**

     * 显示存储的剩余空间

     */

    fun showAvailableSize() {
        //手机内部存储大小
        val romSize = getAvailSpace(Environment.getDataDirectory().absolutePath+"/user/0/com.gdeer.gdtesthub")

        //外部存储大小
        val sdSize = getAvailSpace(Environment.getExternalStorageDirectory().absolutePath)

        Log.d("zhangjl", "内存可用空间: " + Formatter.formatFileSize(this, romSize))
        Log.d("zhangjl", "SD卡可用空间: " + Formatter.formatFileSize(this, sdSize))
    }

    /**
     * 获取某个目录的可用空间
     */
    fun getAvailSpace(path: String): Long {

        var statfs = StatFs(path)

        //获取分区的大小
        val size = statfs.blockSizeLong

        //获取可用分区块的个数
        val count = statfs.availableBlocksLong

        return (size * count)
    }
}
