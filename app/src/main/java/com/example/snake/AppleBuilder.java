package com.example.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

class AppleBuilder extends GameObject{

    public ArrayList<Apple> spawnedApples = new ArrayList<Apple>();
    // The location of the apple on the grid
    // Not in pixels
    //private ArrayList<Point> location;

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    private int mSize;
    private Context context;

    // An image to represent the apple
    private Bitmap mBitmapApple;

    /// Set up the apple in the constructor
    AppleBuilder(Context context, Point sr, int s){
        this.context = context;
        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an apple
        mSize = s;
    }

    void spawnApple () {
        Random random = new Random();
        if (random.nextInt(10) + 1 <= 2)
            spawnedApples.add(createBadApple());
        else
            spawnedApples.add(createGoodApple());
    }

    Apple createGoodApple() {
        // Load the right apple image into the apple bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
        // Resize the Bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, mSize, mSize, false);
        return new GoodAppleBuilder(mSpawnRange, mBitmapApple).setPoints().spawnLocation().build();
    }

    Apple createBadApple() {
        // Load the right apple image into the apple bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.bad_apple);
        // Resize the Bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, mSize, mSize, false);
        return new BadAppleBuilder(mSpawnRange, mBitmapApple).setPoints().spawnLocation().build();
    }

    // Draw the apple
    void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < spawnedApples.size(); ++i) {
            canvas.drawBitmap(spawnedApples.get(i).getBitmap(),
                    spawnedApples.get(i).getLocation().x * mSize,
                    spawnedApples.get(i).getLocation().y * mSize, paint);

        }
    }

}