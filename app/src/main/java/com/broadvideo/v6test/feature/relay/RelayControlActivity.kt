package com.broadvideo.v6test.feature.relay

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.broadvideo.v6test.BaseActivity
import com.broadvideo.v6test.R
import com.broadvideo.v6test.feature.light.XinhuaGpioUtil
import com.broadvideo.v6test.feature.screen.ScreenControlActivity
import com.broadvideo.v6test.feature.sound.SoundTestActivity
import com.serialport.assistant.Serialdata
import kotlinx.android.synthetic.main.activity_relay.*

class RelayControlActivity : BaseActivity(), View.OnClickListener {
    private val GPIO5 = "/sys/class/xh_custom/xh_custom_gpio/device/gpio5"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_relay)
        setTestInfo("RelayControl")
        ll_open_relay.setOnClickListener(this)
        ll_close_relay.setOnClickListener(this)
    }


    override fun setNextIntent() {
        var i = Intent(this@RelayControlActivity, SoundTestActivity::class.java)
        i.putExtra("key", "auto_test")
        nextIntent  =i
    }

    override fun countDown(countDown: Int) {
        if (countDown % 2 != 0){
            setTestInfo("Open Relay")
            XinhuaGpioUtil.RootCommand("echo 1 > $GPIO5") //开
        }else{
            setTestInfo("CLose Relay")
            XinhuaGpioUtil.RootCommand("echo 0 > $GPIO5") //开
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ll_open_relay ->{
                setTestInfo("Open Relay")
                XinhuaGpioUtil.RootCommand("echo 1 > $GPIO5") //开
            }
            R.id.ll_close_relay->{
                XinhuaGpioUtil.RootCommand("echo 0 > $GPIO5") //开
                setTestInfo("CLose Relay")
            }
        }
    }
}