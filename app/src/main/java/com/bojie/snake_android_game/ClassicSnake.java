package com.bojie.snake_android_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

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
    private TextView textScore;

    private boolean useButtons;
    private int playerScore;
    private boolean gameOver = false;
    private ArrayList<ImageView> parts;
    private int screenHeight, screenWidth;
    private ArrayList<ImageView> points;
    private boolean isCollide = false;
    private Handler myHandle;
    private ImageView head;


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
        textScore = (TextView) findViewById(R.id.)
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
        Intent intentScore = new Intent(ClassicSnake.this, ClassicScore.class);
        intentScore.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentScore);
    }

    private void checkBitten() {
        ImageView snakeHead = parts.get(0);
        ImageView snakeTile = new ImageView(this);

        for (int i = 1; i < parts.size(); i++) {
            snakeTile = parts.get(i);
            if (snakeHead.getX() == snakeTile.getX() && snakeHead.getY() == snakeTile.getY()) {
                collide();
                break;
            }
        }
    }

    private void addTail() {
        RelativeLayout snakeLayout = (RelativeLayout) findViewById(R.id.snake_layout);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.head);
        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams((screenWidth * 20) / 450, (screenHeight * 30) / 450);
        imageView.setLayoutParams(layoutParams);
        snakeLayout.addView(imageView);
        parts.add(imageView);
    }

    private void setNewPoint() {
        Random random = new Random();
        ImageView newPoint = new ImageView(ClassicSnake.this);
        float x = random.nextFloat() * (screenWidth - newPoint.getWidth());
        float y = random.nextFloat() * (screenHeight - newPoint.getHeight());
        newPoint.setImageResource(R.mipmap.food);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ((screenWidth * 20) / 450), ((screenHeight * 30) / 450));
        newPoint.setLayoutParams(layoutParams);
        newPoint.setX(x);
        newPoint.setY(y);
        isCollide = false;
        classicSnakeLayout.addView(newPoint);
        points.add(points.size(), newPoint);
    }

    private void setFoodPoints() {
        for (int i = 0; i < GameSettings.FOOT_POINTS; i++) {
            Random rand = new Random();
            ImageView foodItem = new ImageView(this);
            float x = rand.nextFloat() * (screenWidth - foodItem.getWidth());
            float y = rand.nextFloat() * (screenHeight - foodItem.getWidth());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ((screenWidth * 20) / 450), ((screenHeight * 30) / 450));
            foodItem.setImageResource(R.mipmap.food);
            foodItem.setLayoutParams(layoutParams);
            foodItem.setX(x);
            foodItem.setY(y);
            classicSnakeLayout.addView(foodItem);
            points.add(i, foodItem);
        }
    }

    private void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!gameOver && !isPaused) {
                    try {
                        Thread.sleep(GameSettings.GAME_THREAD);
                        myHandle.post(new Runnable() {
                            @Override
                            public void run() {
                                float left = head.getX() - head.getWidth();
                                float top = head.getY() - head.getHeight();
                                float right = head.getX() + head.getWidth();
                                float bottom = head.getY() + head.getHeight();

                                for (int i = 0; i < points.size(); i++) {
                                    if (!isCollide) {
                                        ImageView p = points.get(i);
                                        float left1 = p.getX() - p.getWidth();
                                        float top1 = p.getY() - p.getHeight();
                                        float right1 = p.getX() + p.getWidth();
                                        float bottom1 = p.getY() + p.getHeight();

                                        // Player
                                        Rect rc1 = new Rect();
                                        rc1.set((int) left, (int) top, (int) right, (int) bottom);
                                        //Food Item
                                        Rect rc2 = new Rect();
                                        rc2.set((int) left1, (int) top1, (int) right1, (int) bottom1);

                                        p.getHitRect(rc2);
                                        if (Rect.intersects(rc1, rc2)) {
                                            classicSnakeLayout.removeView(p);
                                            points.remove(i);
                                            playerScore++;
                                            isCollide = true;
                                            textScore.setText("Score: " + playerScore);
                                            setNewPoint();
                                            addTail();
                                            shake();
                                            fadeAnim();
                                        }
                                        checkBitten();
                                    }
                                }
                                isCollide = false;
                            }
                        });
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }
}
