package com.gdeer.gdtesthub.view.textureview

import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.os.Bundle
import android.view.TextureView
import androidx.appcompat.app.AppCompatActivity

class LiveCameraActivity : AppCompatActivity(), TextureView.SurfaceTextureListener {
    lateinit var mCamera: Camera
    lateinit var mTextureView: TextureView

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        // Ignored, Camera does all the work for us
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        // Invoked every time there's a new Camera preview frame
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        mCamera.stopPreview()
        mCamera.release()
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        mCamera = Camera.open()

        mCamera.setPreviewTexture(surface)
        mCamera.startPreview()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTextureView = TextureView(this)
        mTextureView.surfaceTextureListener = this

        setContentView(mTextureView)
    }
}
