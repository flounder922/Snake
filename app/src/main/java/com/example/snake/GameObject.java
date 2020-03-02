package com.example.snake;

import android.content.Context;
import android.graphics.Point;

class GameObject {
    protected int blockSize;
    protected Point gameObjectPoint;
    protected Context context;

    GameObject(Context context, Point size, int blockSize) {
        this.blockSize = blockSize;
        gameObjectPoint = size;
        this.context = context;
    }
}
