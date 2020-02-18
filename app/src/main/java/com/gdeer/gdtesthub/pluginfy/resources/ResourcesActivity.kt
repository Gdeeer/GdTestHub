package com.gdeer.gdtesthub.pluginfy.resources

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.reflect.MethodUtils
import com.gdeer.gdtesthub.utils.FileUtil
import kotlinx.android.synthetic.main.activity_resources.*

class ResourcesActivity : AppCompatActivity() {

    companion object {
        const val PLUGIN_APK_NAME = "simple.apk"
    }

    var pluginApkPath: String = ""
    var mResources: Resources? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        // 将 simple.apk 复制到 /data/data/packageName/files
        val apkFile = newBase?.getFileStreamPath(PLUGIN_APK_NAME)
        pluginApkPath = apkFile?.absolutePath ?: ""
        FileUtil.copyFileFromAssets(newBase, PLUGIN_APK_NAME, apkFile, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)

        // 创建新的 Resources 对象
        loadResources()
        // 7f0b0028，即 simple.apk 的 resources.arsc 中 R.string.hello_str 的 id 值
        val s = mResources?.getString("7f0b0028".toInt(16))
        tv_plugin_res_string.text = s
    }

    private fun loadResources() {
        // 创建新的 AssetManager 对象
        val mAssetManager = AssetManager::class.java.newInstance()
        // 将 simple.apk 的路径传入 AssetManager
        MethodUtils.invokeMethod(mAssetManager, "addAssetPath", pluginApkPath)
        // 通过 AssetManager 创建 Resources
        mResources = Resources(mAssetManager, resources.displayMetrics, resources.configuration)
    }
}
