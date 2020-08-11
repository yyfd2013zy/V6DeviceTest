package com.broadvideo.v6test.feature.screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.xhapimanager.XHApiManager
import com.broadvideo.v6test.BaseActivity
import com.broadvideo.v6test.R
import com.broadvideo.v6test.feature.light.XinhuaGpioUtil
import com.broadvideo.v6test.feature.relay.RelayControlActivity
import kotlinx.android.synthetic.main.activity_screen.*

class ScreenControlActivity : BaseActivity(), View.OnClickListener {
    private var apiManager: XHApiManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_screen)
        setTestInfo("ScreenControlTest")
        apiManager = XHApiManager()
        ll_open_screen_light.setOnClickListener(this)
        ll_close_screen_light.setOnClickListener(this)
    }

    override fun setNextIntent() {
        var i = Intent(this@ScreenControlActivity, RelayControlActivity::class.java)
        i.putExtra("key", "auto_test")
        nextIntent = i
    }

    override fun countDown(countDown: Int) {
        if (countDown % 2 != 0){
            setTestInfo("Close Screen")
            apiManager?.XHOpenOrCloseLCD(true)
        }else{
            setTestInfo("Open Screen")
            apiManager?.XHOpenOrCloseLCD(false)
        }
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ll_open_screen_light ->{
                apiManager?.XHOpenOrCloseLCD(true)
            }
            R.id.ll_close_screen_light ->{
                apiManager?.XHOpenOrCloseLCD(false)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        apiManager?.XHOpenOrCloseLCD(true)
    }
}