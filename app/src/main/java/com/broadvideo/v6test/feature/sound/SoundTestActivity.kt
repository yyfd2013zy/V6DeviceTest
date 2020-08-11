package com.broadvideo.v6test.feature.sound

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
import com.serialport.assistant.Serialdata
import kotlinx.android.synthetic.main.activity_light.*
import kotlinx.android.synthetic.main.activity_sound.*
import kotlinx.android.synthetic.main.activity_tem.*

class SoundTestActivity : BaseActivity(), View.OnClickListener {
    private var soundPlayControl: SoundPlayControl? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_sound)
        setTestInfo("SoundTest")
        ll_playSound.setOnClickListener(this)
        soundPlayControl = SoundPlayControl(this)
    }

    override fun setNextIntent() {

    }

    override fun countDown(countDown: Int) {
        if (countDown == 2) {
            soundPlayControl?.playHeighTipSound(false)
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_playSound -> {
                setTestInfo("Play Sound")
                soundPlayControl?.playHeighTipSound(true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPlayControl?.stopPlay()
        soundPlayControl?.destory()
    }
}