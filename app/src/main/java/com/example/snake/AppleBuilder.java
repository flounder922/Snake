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

    public ArrayList<Apple> spawnedApples;
    // The location of the apple on the grid
    // Not in pixels
    private ArrayList<Point> location;

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    private int mSize;

    // An image to represent the apple
    private Bitmap mBitmapApple;

    /// Set up the apple in the constructor
    AppleBuilder(Context context, Point sr, int s){
        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an apple
        mSize = s;

        // Load the image to the bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);

        // Resize the bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);
    }

    void spawnApple () {
        Random random = new Random();
        if (random.nextInt(10) <= 1)
            spawnedApples.add(createBadApple());
        else
            spawnedApples.add(createGoodApple());
    }

    Apple createGoodApple() {
        return new GoodAppleBuilder(mSpawnRange).setPoints().spawnLocation().build();
    }

    Apple createBadApple() {
        return new BadAppleBuilder(mSpawnRange).setPoints().spawnLocation().build();
    }

    // This is called every time an apple is eaten
/*    void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }*/

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    ArrayList<Point> getLocation(){
         for (int i = 0; i < spawnedApples.size(); ++i) {
             location.add(spawnedApples.get(i).location);
         }
        return location;
    }

    // Draw the apple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapApple,
                location.x * mSize, location.y * mSize, paint);

    }

}