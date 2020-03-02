package com.example.snake;

import android.graphics.Bitmap;
import android.graphics.Point;

interface Apple {
    int points = 0;
    Point location = null;
    Bitmap bitmapApple = null;

    Apple setPoints();
    Apple build();
    Apple spawnLocation();

}
