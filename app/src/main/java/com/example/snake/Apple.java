package com.example.snake;

import android.graphics.Bitmap;
import android.graphics.Point;

interface Apple {
    Apple setPoints();
    Apple build();
    Apple spawnLocation();
    Bitmap getBitmap();
    Point getLocation();
    int appleEaten();
    int getX();
    int getY();
}
