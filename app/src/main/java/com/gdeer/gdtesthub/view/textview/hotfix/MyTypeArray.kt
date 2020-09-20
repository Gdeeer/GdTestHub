package android.content.res;

import android.content.res.Resources
import android.content.res.TypedArray
import android.util.Log
import com.gdeer.gdtesthub.view.textview.hotfix.HotTestMap

class MyTypeArray(resources: Resources?) : TypedArray(resources) {
    override fun getText(index: Int): CharSequence {
        if (!HotTestMap.map.isEmpty()) {
            val textId = getResourceId(index, Resources.ID_NULL)
            val textIdName = try {
                resources.getResourceEntryName(textId)
            } catch (e: Exception) {
                ""
            }
            val keySet = HotTestMap.map.keys
            if (keySet.contains(textIdName)) {
                Log.d("zhangjl", "MyResources replace: id = $textId idName: $textIdName")
                return HotTestMap.map[textIdName] ?: ""
            }
        }
        return super.getText(index)
    }
}