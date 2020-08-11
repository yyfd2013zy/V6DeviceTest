package com.broadvideo.v6test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*
import android.view.View.FOCUS_DOWN
import android.view.ViewTreeObserver
import com.broadvideo.v6test.feature.distance.DistanceTestActivity


abstract class BaseActivity : AppCompatActivity() {
    private var infoMsg = ""
    private val HANDLER_COUNTDOWN = 999
    private var cutDownSum = 0
    var nextIntent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setNextIntent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        if (intent.getStringExtra("key").equals("auto_test")) {//is auto test
            countDownHandler.sendMessageDelayed(buildMessage(), 1000)
        }
    }

    abstract fun setNextIntent()
    abstract fun countDown(countDown:Int)

    fun bindView(layoutId: Int) {
        var contentView = layoutInflater.inflate(layoutId, null)
        ll_content.addView(contentView)
    }

    var countDownHandler = object : Handler() {
        override fun dispatchMessage(msg: Message) {
            super.dispatchMessage(msg)
            when (msg.obj) {
                HANDLER_COUNTDOWN -> {
                    if (cutDownSum < 10) {
                        cutDownSum++
                        countDown(cutDownSum)
                        setTestProgress(cutDownSum)
                        sendMessageDelayed(buildMessage(), 1000)
                    } else {
                        startActivity(nextIntent)
                        finish()
                    }

                }
            }
        }
    }

    private fun buildMessage(): Message {
        var m = Message()
        m.obj = HANDLER_COUNTDOWN
        return m
    }

    fun setTestInfo(msg: String) {
        infoMsg = infoMsg + "\n" + msg
        tv_test_info.text = infoMsg
        scrollView.getViewTreeObserver()
            .addOnGlobalLayoutListener({
                scrollView.post({ scrollView.fullScroll(View.FOCUS_DOWN) })
            })
    }

    fun setTestProgress(pro: Int) {
        pb.progress = pro
    }
}