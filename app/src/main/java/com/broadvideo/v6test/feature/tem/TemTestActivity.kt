package com.broadvideo.v6test.feature.tem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.broadvideo.v6test.BaseActivity
import com.broadvideo.v6test.R
import com.broadvideo.v6test.feature.light.LightTestActivity
import com.serialport.assistant.Serialdata
import kotlinx.android.synthetic.main.activity_tem.*

class TemTestActivity : BaseActivity() {


    var serialdata: Serialdata? = null//测温模块
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_tem)
        setTestInfo("TemTest")
        initTemCore()
    }
    override fun setNextIntent() {
        var i = Intent(this, LightTestActivity::class.java)
        i.putExtra("key", "auto_test")
        nextIntent = i
    }

    override fun countDown(countDown: Int) {

    }

    var mHandler: Handler? = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    setTestInfo("Read TemData Success")
                   var tem = msg.obj as Float
                    tv_info.text = tem.toString()
                }
            }
        }
    }

    private fun initTemCore() {
        serialdata = Serialdata(mHandler)//初始化对象
        serialdata?.setiBaudRate("/dev/ttyS4", 115200)// 设置串口号和波特率
        serialdata?.InitSerial()// 完成串口的初始化；
        setTestInfo("initTemCore")
    }

    override fun onResume() {
        super.onResume()
        serialdata?.resTempData()//恢复接收串口数据
    }

    override fun onPause() {
        super.onPause()
        serialdata?.StopUpdate()//停止接收数据
    }

    override fun onDestroy() {
        super.onDestroy()
        serialdata?.closeSerial()//关闭串口
    }
}