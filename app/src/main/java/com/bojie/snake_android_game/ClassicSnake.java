package com.bojie.snake_android_game;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class ClassicSnake extends AppCompatActivity {

    private boolean mPlayMusic;
    private MediaPlayer mMusicPlayer;

    private RelativeLayout classicSnakeLayout;
    private boolean isInitialized;

    private GestureDetector mGestureDetector;
    private boolean isPaused;

    private boolean isGoingLeft = false;
    private boolean isGoingUp = false;
    private boolean isGoingRight = false;
    private boolean isGoingDown = false;

    private boolean clickRight;
    private boolean clickLeft;
    private boolean clickDown;
    private boolean clickUp;

    public static final String KEY_SNAKE_PREFERENCES = "SnakePreferences";
    public static final String KEY_PLAY_MUSIC = "PlayMusic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_snake);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        musicOnOff();
        classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
        classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
        classicSnakeLayout.setPaddingRelative(GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING,
                GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING);
        isInitialized = false;
    }

    private void musicOnOff() {
        SharedPreferences preferences = getApplicationContext()
                .getSharedPreferences(KEY_SNAKE_PREFERENCES, Context.MODE_PRIVATE);
        mPlayMusic = preferences.getBoolean(KEY_PLAY_MUSIC, true);
        mMusicPlayer = MediaPlayer.create(ClassicSnake.this, R.raw.music);
        if (mPlayMusic) {
            mMusicPlayer.setLooping(true);
            mMusicPlayer.start();
        } else {
            mMusicPlayer.stop();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
        mMusicPlayer.release();
    }

    public void onSwipeRight() {
        if (isGoingRight && isGoingLeft) {
            isGoingRight = true;
            isGoingLeft = false;
            isGoingDown = false;
            isGoingUp = false;
        }
    }

    public void onSwipeLeft() {
        if (isGoingRight && isGoingLeft) {
            isGoingRight = false;
            isGoingLeft = true;
            isGoingDown = false;
            isGoingUp = false;
        }
    }

    public void onSwipeDown() {
        if (isGoingUp && isGoingDown) {
            isGoingRight = false;
            isGoingLeft = false;
            isGoingDown = true;
            isGoingUp = false;
        }
    }

    public void onSwipeUp() {
        if (isGoingDown && isGoingUp) {
            isGoingRight = false;
            isGoingLeft = false;
            isGoingDown = false;
            isGoingUp = true;
        }
    }

    private void clickRight() {
        if (clickRight && clickLeft) {
            clickRight = true;
            clickLeft = false;
            clickDown = false;
            clickUp = false;
        }
    }

    private void clickLeft() {
        if (clickRight && clickLeft) {
            clickRight = false;
            clickLeft = true;
            clickDown = false;
            clickUp = false;
        }
    }

    private void clickDown() {
        if (clickDown && clickUp) {
            clickRight = false;
            clickLeft = false;
            clickDown = true;
            clickUp = false;
        }
    }

    private void clickUp() {
        if (clickDown && clickUp) {
            clickRight = false;
            clickLeft = false;
            clickDown = false;
            clickUp = true;
        }
    }
}
