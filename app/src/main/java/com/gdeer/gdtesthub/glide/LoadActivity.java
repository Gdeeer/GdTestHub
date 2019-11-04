package com.gdeer.gdtesthub.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gdeer.gdtesthub.R;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

public class LoadActivity extends AppCompatActivity {

    ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        String url = "http://wx1.sinaimg.cn/mw600/006r8Ylqly1fusrsnkb2ej30g40nmk4m.jpg";
        mIv = findViewById(R.id.iv_load);


        findViewById(R.id.bt_start_load).setOnClickListener(v ->
            loadIntoIv(url)
        );

        findViewById(R.id.bt_start_get).setOnClickListener(v ->
            getBitmap(url)
        );

        findViewById(R.id.bt_start_load_null).setOnClickListener(v ->
            loadIntoNull(url)
        );

        findViewById(R.id.bt_start_pre_load).setOnClickListener(v ->
            preload(url)
        );

        // TODO: 2019/11/1 加入 data/data/cache 里的文件查看
    }

    @NotNull
    private ViewTarget<ImageView, Drawable> loadIntoIv(String url) {
        return Glide.with(this)
            .load(url)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .onlyRetrieveFromCache(true)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(mIv);
    }

    private void getBitmap(String url) {
        new Thread(() -> {
            try {
                Bitmap bitmap = Glide.with(LoadActivity.this)
                    .asBitmap()
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .submit()
                    .get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @NotNull
    private SimpleTarget<Drawable> loadIntoNull(String url) {
        return Glide.with(this)
            .load(url)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                }
            });
    }

    @NotNull
    private Target<Drawable> preload(String url) {
        return Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .preload(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }
}
