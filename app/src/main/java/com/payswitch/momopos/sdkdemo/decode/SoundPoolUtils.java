package com.payswitch.momopos.sdkdemo.decode;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by admin on 2018/3/15.
 */

public class SoundPoolUtils {
    private SoundPool soundPool;
    public int soundID;

    public SoundPoolUtils() {
        if(soundPool==null)
            initSoundPool();
    }

    public void close()
    {
        if(soundPool!=null) {
            soundPool.release();
            soundPool=null;
        }
    }

    public void initSound(Context context,int id) {
        soundID =  soundPool.load(context, id, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

            }
        });
    }

    private void initSoundPool()
    {
        if (Build.VERSION.SDK_INT >= 21) {
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

    }

    public void soundPlay(Context context) {
//        //第一个参数Context,第二个参数资源Id，第三个参数优先级
         if (soundPool == null){
            initSoundPool();
         }
         soundPool.play(soundID, 1, 1, 0, 0, 1);
    }
}
