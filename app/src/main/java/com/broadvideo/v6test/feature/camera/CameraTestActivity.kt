package com.broadvideo.v6test.feature.camera

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.broadvideo.v6test.BaseActivity
import com.broadvideo.v6test.Constant
import com.broadvideo.v6test.R
import com.broadvideo.v6test.feature.camera.CameraManager
import com.broadvideo.v6test.feature.camera.CameraPreviewData
import com.broadvideo.v6test.feature.distance.DistanceTestActivity
import kotlinx.android.synthetic.main.activity_camera.*

class CameraTestActivity : BaseActivity() {
    private var manager: CameraManager? = null
    private var managerIr: CameraManager? = null
    private var cameraIOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_camera)
        setTestInfo("CameraTest")
        openCamera()
    }

    override fun setNextIntent() {
        var i = Intent(this, DistanceTestActivity::class.java)
        i.putExtra("key", "auto_test")
        nextIntent = i
    }

    override fun countDown(countDown: Int) {

    }


    private fun openCamera() {
        manager = CameraManager()
        manager?.setPreviewDisplay(preview)
        manager?.setListener(object : CameraManager.CameraListener {
            override fun onOpenResult(success: Boolean) {
                if (success) {
                    runOnUiThread({
                        setTestInfo("CameraOpenSuccess")
                    })
                } else {
                    runOnUiThread({
                        setTestInfo("CameraOpenError")
                    })
                }
            }

            override fun onPictureTaken(cameraPreviewData: CameraPreviewData?) {
                //yuv data call back，do you detect
            }
        })

        managerIr = CameraManager()
        managerIr?.setPreviewDisplay(preview_ir)
        managerIr?.setListener(object : CameraManager.CameraListener {
            override fun onOpenResult(success: Boolean) {
                if (success) {
                    runOnUiThread({
                        setTestInfo("IR CameraOpenSuccess")
                    })
                } else {
                    runOnUiThread({
                        setTestInfo("IR CameraOpenError")
                    })
                }
            }

            override fun onPictureTaken(cameraPreviewData: CameraPreviewData?) {
                //yuv data call back ，do you detect
            }

        })
        manager?.open(windowManager, false, 0, 0)
        managerIr?.open(windowManager, true, 0, 0)
        cameraIOpen = true

    }

    override fun onDestroy() {
        super.onDestroy()
        manager?.release()
        managerIr?.release()

    }
}