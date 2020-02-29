package com.example.snake;

public class Point {
    // The location variables on a 2D screen.
    public int x;
    public int y;

    // Base constructor for the point class
    Point(){
        this.x = 0;
        this.y = 0;
    }

    // Overloaded constructor that accepts the coordinates upon creation.
    Point( int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Check to see if coordinates are the same.
    public final boolean equals(int x, int y) {
        if (this.x == x && this.y == y)
            return true;
        else
            return false;
    }

    // Change the position of the point
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
