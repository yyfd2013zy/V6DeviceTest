package com.broadvideo.v6test.feature.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;

import com.broadvideo.v6test.R;

import java.util.Locale;

public class SoundPlayControl {
    private final String TAG = "SoundPlayControl";
    protected SoundPool soundPool;//本地语音播报
    private Context mContext;
    private float volumeRatio;
    private boolean spundPoolInitOk = false;

    public int highTemTipsSound;

    public SoundPlayControl(Context context) {
        this.mContext = context;
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else locale = Locale.getDefault();

        initSoundPool();
        initSoundResource();
    }


    private void initSoundPool() {
        AudioManager am = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
        float audioMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volumeCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeRatio = audioMaxVolume;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder builder = new SoundPool.Builder();
            //传入音频的数量
            builder.setMaxStreams(1);
            //AudioAttributes是一个封装音频各种属性的类
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            //设置音频流的合适属性
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            builder.setAudioAttributes(attrBuilder.build());
            soundPool = builder.build();
        } else {
            //第一个参数是可以支持的声音数量，第二个是声音类型，第三个是声音品质
            soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        }
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                spundPoolInitOk = true;
            }
        });
    }

    //    private int measureingTem,reMeasure,normalTem,errorTem;
    private void initSoundResource() {
        highTemTipsSound = soundPool.load(mContext, R.raw.siren, 1);
    }

    /**
     * Native方式播放语音
     *
     * @param soundId 语音类型
     */
    public void playSound(final int soundId) {
        if (!spundPoolInitOk || soundPool == null) {
            return;
        }
        soundPool.play(soundId,
                volumeRatio,// 左声道音量
                volumeRatio,// 右声道音量
                1, // 优先级
                0,// 循环播放次数
                1);// 回放速度，该值在0.5-2.0之间 1为正g常速度
    }


    public void playHeighTipSound(boolean stop) {
        playSound(highTemTipsSound);
        if (stop) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    soundPool.stop(highTemTipsSound);
                }
            }, 1500);
        }
    }

    public void stopPlay() {
        if (soundPool != null)
            soundPool.stop(highTemTipsSound);
    }

    public void destory() {
        soundPool = null;
    }

}
