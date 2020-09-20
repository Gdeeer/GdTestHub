package com.gdeer.gdtesthub.view.textview.hotfix;

import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log

class MyResources(assets: AssetManager?, metrics: DisplayMetrics?, config: Configuration?) : Resources(assets, metrics, config) {
    override fun getText(id: Int): CharSequence {
        if (!HotTestMap.map.isEmpty()) {
            val textIdName = try {
                getResourceEntryName(id)
            } catch (e: Exception) {
                ""
            }
            val keySet = HotTestMap.map.keys
            if (keySet.contains(textIdName)) {
                Log.d("zhangjl", "MyResources replace: id = $id idName: $textIdName")
                return HotTestMap.map[textIdName] ?: ""
            }
        }

        return super.getText(id)
    }
}