package com.example.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

public class SnakeComposite {
    public Snake snake;

    SnakeComposite(Context context, Point mr, int ss) {
        snake = new Snake(context, mr, ss);
    }

    void move() {
        snake.move();
    }

    boolean detectDeath() {
        return snake.detectDeath();
    }

    boolean checkDinner(Point l) {
        if( snake.checkDinner(l) ) {
            snake.add(new Point(-10, -10));
            return true;
        }
        return false;
    }

    void draw(Canvas canvas, Paint paint) {
        snake.draw(canvas, paint);
    }

    void changeDirection(MotionEvent motionEvent) {
        snake.switchHeading(motionEvent);
    }

    void reset(int w, int h) {
        snake.reset(w, h);
    }
}
