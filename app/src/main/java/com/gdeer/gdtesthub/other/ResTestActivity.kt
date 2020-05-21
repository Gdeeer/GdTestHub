package com.gdeer.gdtesthub.other

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myandroidlib.ResTestLib
import com.example.myandroidlib2.ResTestLib2
import com.gdeer.gdtesthub.R

class ResTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_test)

        // 报错
        // java.lang.NoSuchFieldError: No static field ll_android_lib_2 of type I in class Lcom/example/myandroidlib2/R$id; or its superclasses (declaration of 'com.example.myandroidlib2.R$id' appears in /data/app/com.gdeer.gdtesthub-C1ZBerfz_XSuJTBp3rQcUw==/base.apk!classes2.dex)
        // androidLib 里的 layout_android_lib 覆盖了 androidLib2 里的 layout_android_lib
        // 所以 androidLib2 里的 layout_android_lib 里的 ll_android_lib_2 找不到了
        ResTestLib.printResId()
        ResTestLib2.printResId()
    }
}
