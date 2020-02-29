package com.example.snake;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class PreLollipopSoundManager extends SoundManager {

    // for playing sound effects
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;

    PreLollipopSoundManager(Context context) {
        super(context);

        mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }

    public void playEatSound(){
        // Play a sound
        mSP.play(mEat_ID, 1, 1, 0, 0, 1);
    }

    public void playDeathSound() {
        mSP.play(mCrashID, 1, 1, 0, 0, 1);
    }
}