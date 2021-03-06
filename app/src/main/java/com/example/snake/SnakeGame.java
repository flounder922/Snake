package com.example.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class SnakeGame extends SurfaceView implements Runnable {

    // Objects for the game loop/thread
    private Thread mThread = null;

    // Control pausing between updates
    private long mNextFrameTime;

    // Is the game currently playing and or paused?
    private volatile boolean mPlaying = false;
    private volatile boolean mPaused = true;

    // SoundManager
    SoundManager mSoundManager;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;

    // How many points does the player have
    private int mScore;

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    // GameObject variables
    private SnakeComposite mSnake;
    private AppleBuilder mApple;

    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);
        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mSoundManager = new SoundManager(new PostLollipopSoundManager(context));
        else
            mSoundManager = new SoundManager(new PreLollipopSoundManager(context));

        // Call the constructors of our two game objects
        mApple = new AppleBuilder(context,
                new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize);

        mSnake = new SnakeComposite(context,
                new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize);
    }

    // Called to start a new game
    public void newGame() {
        // reset the snake
        mSnake.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner
        mApple.spawnedApples.add(mApple.createGoodApple());

        // Reset the mScore
        mScore = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();
    }

    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                }
            }
            draw();
        }
    }

    // Check to see if it is time for an update
    public boolean updateRequired() {
        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(mNextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed
            // Setup when the next update will be triggered
            mNextFrameTime =System.currentTimeMillis() + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }
        return false;
    }

    // Update all the game objects
    public void update() {
        // Move the snake
        mSnake.move();

        // Did the head of the snake eat the apple?
        if(mSnake.eatApple(mApple.spawnedApples)) {
            int i = mSnake.checkDinner(mApple.spawnedApples);
            // One day the apple will be ready!
            // Add to  mScore
            mScore += mApple.spawnedApples.get(i).appleEaten();
            mApple.spawnedApples.remove(i);
            mApple.spawnApple();
            mApple.spawnApple();
            mApple.spawnApple();
            mApple.spawnApple();
            mApple.spawnApple();

            if((mScore % 5) > 0 && (mScore / 5) > 1) {
                int numberOfApples = mScore / 5;
                while(numberOfApples > mApple.spawnedApples.size())
                    mApple.spawnApple();
            }

            // Play a sound
            mSoundManager.strategyPlayEatSound();
        }

        // Did the snake die?
        if (mSnake.detectDeath()) {
            mSoundManager.strategyPlayDeathSound();
            // Pause the game ready to start again
            mPaused =true;
        }

        if(mScore < 0) {
            mSoundManager.strategyPlayDeathSound();
            mPaused = true;
        }
    }


    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            // Objects for drawing
            Canvas mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the score
            mCanvas.drawText("" + mScore, 20, 120, mPaint);

            // Draw the apple and the snake
            mApple.draw(mCanvas, mPaint);
            mSnake.draw(mCanvas, mPaint);

            // Draw some text while paused
            if(mPaused) {

                // Set the size and color of the mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(250);

                // Draw the message
                // We will give this an international upgrade soon
                //mCanvas.drawText("Tap To Play!", 200, 700, mPaint);
                mCanvas.drawText("Tap to Play!", 200, 700, mPaint);
            }
            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (mPaused) {
                    mPaused = false;
                    newGame();
                    // Don't want to process snake direction for this tap
                    return true;
                }
                // Let the Snake class handle the input
                mSnake.changeDirection(motionEvent);
                break;
            default:
                break;
        }
        return true;
    }

    // Stop the thread
    public void pause() {
        mPlaying = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    // Start the thread
    public void resume() {
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }
}