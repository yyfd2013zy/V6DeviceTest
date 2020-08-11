package com.broadvideo.v6test.feature.light

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.broadvideo.v6test.BaseActivity
import com.broadvideo.v6test.R
import com.broadvideo.v6test.feature.screen.ScreenControlActivity
import com.broadvideo.v6test.feature.tem.TemTestActivity
import com.serialport.assistant.Serialdata
import kotlinx.android.synthetic.main.activity_light.*
import kotlinx.android.synthetic.main.activity_tem.*

class LightTestActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_light)
        setTestInfo("LightTest")
        ll_open_flash_light.setOnClickListener(this)
        ll_close_flash_light.setOnClickListener(this)
        ll_open_ir_light.setOnClickListener(this)
        ll_close_ir_light.setOnClickListener(this)
    }

    override fun setNextIntent() {
        var i = Intent(this, ScreenControlActivity::class.java)
        i.putExtra("key", "auto_test")
        nextIntent = i
    }

    override fun countDown(countDown: Int) {
        if (countDown % 2 != 0){
            setTestInfo("Open Light")
            XinhuaGpioUtil.RootCommand("echo " + 1 + " > " + "/sys/class/xh_custom/xh_custom_gpio/device/gpio4")
        }else{
            setTestInfo("Close  Light")
            XinhuaGpioUtil.RootCommand("echo " + 0 + " > " + "/sys/class/xh_custom/xh_custom_gpio/device/gpio4")
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ll_open_flash_light ->{
                XinhuaGpioUtil.RootCommand("echo " + 1 + " > " + "/sys/class/xh_custom/xh_custom_gpio/device/gpio4")
            }
            R.id.ll_close_flash_light->{
                XinhuaGpioUtil.RootCommand("echo " + 0 + " > " + "/sys/class/xh_custom/xh_custom_gpio/device/gpio4")
            }
            R.id.ll_open_ir_light ->{

            }
            R.id.ll_close_ir_light->{

            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}