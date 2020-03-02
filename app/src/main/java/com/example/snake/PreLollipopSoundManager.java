package com.example.snake;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class PreLollipopSoundManager implements SoundStrategy {

    // for playing sound effects
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;

    PreLollipopSoundManager(Context context) {
        mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }

    public void playEatSound() {
        mSP.play(mEat_ID, 1, 1, 0, 0, 1);
    }

    public void playDeathSound() {
        mSP.play(mCrashID, 1, 1, 0, 0, 1);
    }
}
