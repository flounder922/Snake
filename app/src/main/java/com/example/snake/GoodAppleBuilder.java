package com.example.snake;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.Random;

public class GoodAppleBuilder implements Apple {

    Point location;
    private Point mSpawnRange;
    private int points;
    private Bitmap mBitmapApple;

    GoodAppleBuilder(Point sr, Bitmap bitmapApple) {
        mSpawnRange = sr;
        mBitmapApple = bitmapApple;
        location = sr;
    }

    @Override
    public Apple setPoints() {
        Random random = new Random();
        points = random.nextInt(3) + 1;
        return this;
    }

    @Override
    public Apple build() {
        return this;
    }

    @Override
    public Apple spawnLocation() {
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y) + 1;
        return this;
    }

    @Override
    public Bitmap getBitmap() {
        return mBitmapApple;
    }

    @Override
    public Point getLocation() { return location; }

    @Override
    public int appleEaten() {
        return points;
    }

    @Override
    public int getX() {
        return location.x;
    }

    @Override
    public int getY() {
        return location.y;
    }
}
