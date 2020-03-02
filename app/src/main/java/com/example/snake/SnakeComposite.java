package com.example.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;

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

    int checkDinner(ArrayList<Apple> l) {
        return snake.checkDinner(l);
    }

    boolean eatApple(ArrayList<Apple> apple) {
        return snake.eatApple(apple);
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
