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
}
