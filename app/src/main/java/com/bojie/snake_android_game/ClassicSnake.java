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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

    private ImageView btnRight, btnLeft, btnDown, btnUp;

    private boolean useButtons;
    private int playerScore;
    private boolean gameOver = false;

    public static final String KEY_SNAKE_PREFERENCES = "SnakePreferences";
    public static final String KEY_PLAY_MUSIC = "PlayMusic";
    public static final String KEY_USE_BUTTON_CONTROLS = "UseButtonControls";

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

    private void buttonsDirectionInit() {
        btnRight = (ImageView) findViewById(R.id.btn_right);
        btnLeft = (ImageView) findViewById(R.id.btn_left);
        btnDown = (ImageView) findViewById(R.id.btn_down);
        btnUp = (ImageView) findViewById(R.id.btn_up);

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRight();
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLeft();
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUp();
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDown();
            }
        });


        SharedPreferences preferences = getApplicationContext()
                .getSharedPreferences(KEY_SNAKE_PREFERENCES, Context.MODE_PRIVATE);
        useButtons = preferences.getBoolean(KEY_USE_BUTTON_CONTROLS, true);
        if (useButtons) {
            btnRight.setVisibility(View.VISIBLE);
            btnLeft.setVisibility(View.VISIBLE);
            btnDown.setVisibility(View.VISIBLE);
            btnUp.setVisibility(View.VISIBLE);
        } else {
            btnRight.setVisibility(View.INVISIBLE);
            btnLeft.setVisibility(View.INVISIBLE);
            btnDown.setVisibility(View.INVISIBLE);
            btnUp.setVisibility(View.INVISIBLE);
        }
    }


    private void shake() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(GameSettings.SHAKE_DURATION);
        classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
        classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
        classicSnakeLayout.startAnimation(shake);
    }

    private void fadeAnim() {
        if (playerScore % GameSettings.POINT_ANIMATION == 0) {
            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
            classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake_change);
            classicSnakeLayout.startAnimation(fadeIn);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Animation fadeOut = AnimationUtils.loadAnimation(ClassicSnake.this, R.anim.fade_out);
                    classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
                    classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
                    classicSnakeLayout.startAnimation(fadeOut);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    private void collide() {
        ImageView snakeHead = new ImageView(this);
        gameOver = true;
        SharedPreferences preferences = getApplicationContext()
                .getSharedPreferences(KEY_SNAKE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Score", playerScore);
        editor.commit();
//        Intent intentScore = new Intent(ClassicSnake.this, ClassicScore.class);
//        intentScore.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intentScore);
    }
}
