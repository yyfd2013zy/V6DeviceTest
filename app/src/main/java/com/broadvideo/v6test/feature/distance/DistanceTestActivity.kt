package com.broadvideo.v6test.feature.distance

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.broadvideo.v6test.BaseActivity
import com.broadvideo.v6test.R
import com.broadvideo.v6test.feature.camera.CameraManager
import com.broadvideo.v6test.feature.camera.CameraPreviewData
import com.broadvideo.v6test.feature.tem.TemTestActivity
import com.serialport.assistant.DistanceData
import kotlinx.android.synthetic.main.activity_distance.*

class DistanceTestActivity : BaseActivity() {


    private var distanceData: DistanceData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNextIntent()
        bindView(R.layout.activity_distance)
        setTestInfo("Distance Test")
        initDistanceCore()
    }

    override fun setNextIntent() {
        var i = Intent(this, TemTestActivity::class.java)
        i.putExtra("key", "auto_test")
        nextIntent = i
    }

    override fun countDown(countDown: Int) {

    }

    var mHandler: Handler? = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                2 -> {
                    setTestInfo("Read Distance Data Success!")
                    var distance = msg.obj as Int
                    tv_info.text = distance.toString()
                }
            }
        }
    }

    private fun initDistanceCore() {
        distanceData = DistanceData(mHandler)
        distanceData?.setiBaudRate("/dev/ttyS3", 9600)
        distanceData?.InitDistance()
        setTestInfo("Init Distance Core")
    }


    override fun onDestroy() {
        super.onDestroy()
        distanceData?.closeSerial()
    }
}