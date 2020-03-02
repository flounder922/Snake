package com.example.snake;

import android.graphics.Point;

import java.util.Random;

public class GoodAppleBuilder implements Apple {

    private Point mSpawnRange;

    GoodAppleBuilder(Point sr) {
        mSpawnRange = sr;
    }

    @Override
    public Apple setPoints() {
        return null;
    }

    @Override
    public Apple build() {
        return null;
    }

    @Override
    public Apple spawnLocation() {
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
        return this;
    }
}
