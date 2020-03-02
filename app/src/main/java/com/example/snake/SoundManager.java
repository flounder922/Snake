package com.example.snake;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Build.VERSION;

import java.io.IOException;

public class SoundManager {
    private SoundStrategy strategy;

    public SoundManager(SoundStrategy strategy) {
        this.strategy = strategy;
    }

    public void strategyPlayEatSound() {
        strategy.playEatSound();
    }

    public void strategyPlayDeathSound() {
        strategy.playDeathSound();
    }
    /*SoundManager(Context context) {
        //setupSoundManager(context);
        if (VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();


            mSP = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Prepare the sounds in memory
            descriptor = assetManager.openFd("get_apple.ogg");
            mEat_ID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("snake_death.ogg");
            mCrashID = mSP.load(descriptor, 0);

        } catch (
                IOException e) {
            // Error
        }

    }

    SoundManager setupSoundManager(Context context) {
        // Initialize the SoundPool
        if (VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return new PostLollipopSoundManager(context);
        else
            return new PreLollipopSoundManager(context);
    }

    public void playEatSound(){
        mSP.play(mEat_ID, 1, 1, 0, 0, 1);
    }

    public void playDeathSound() {
        mSP.play(mCrashID, 1, 1, 0, 0, 1);
    }*/
}
