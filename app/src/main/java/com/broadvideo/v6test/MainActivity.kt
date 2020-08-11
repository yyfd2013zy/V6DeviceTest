package com.broadvideo.v6test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.broadvideo.v6test.feature.camera.CameraTestActivity
import com.broadvideo.v6test.feature.distance.DistanceTestActivity
import com.broadvideo.v6test.feature.light.LightTestActivity
import com.broadvideo.v6test.feature.relay.RelayControlActivity
import com.broadvideo.v6test.feature.screen.ScreenControlActivity
import com.broadvideo.v6test.feature.sound.SoundTestActivity
import com.broadvideo.v6test.feature.tem.TemTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        ll_test_camera.setOnClickListener(this)
        ll_test_distance.setOnClickListener(this)
        ll_tem.setOnClickListener(this)
        ll_test_light.setOnClickListener(this)
        ll_screen_control.setOnClickListener(this)
        ll_relay.setOnClickListener(this)
        ll_auto_test.setOnClickListener(this)
        ll_sound.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ll_test_camera ->{
                var inten = Intent(this, CameraTestActivity::class.java)
                inten.putExtra("key","default")
                startActivity(inten)
            }
            R.id.ll_test_distance->{
                var inten = Intent(this, DistanceTestActivity::class.java)
                inten.putExtra("key","default")
                startActivity(inten)
            }
            R.id.ll_tem->{
                var inten = Intent(this, TemTestActivity::class.java)
                inten.putExtra("key","default")
                startActivity(inten)
            }
            R.id.ll_test_light->{
                var inten = Intent(this, LightTestActivity::class.java)
                inten.putExtra("key","default")
                startActivity(inten)
            }
            R.id.ll_screen_control->{
                var inten = Intent(this, ScreenControlActivity::class.java)
                inten.putExtra("key","default")
                startActivity(inten)
            }
            R.id.ll_relay->{
                var inten = Intent(this, RelayControlActivity::class.java)
                inten.putExtra("key","default")
                startActivity(inten)
            }
            R.id.ll_sound->{
                var inten = Intent(this, SoundTestActivity::class.java)
                inten.putExtra("key","default")
                startActivity(inten)
            }
            R.id.ll_auto_test->{
                //start auto test
                var inten = Intent(this, CameraTestActivity::class.java)
                inten.putExtra("key","auto_test")
                startActivityForResult(inten,Constant.REQUEST_CAMERA_TEST_CODE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            Constant.REQUEST_CAMERA_TEST_CODE ->{

            }
        }
    }
}
