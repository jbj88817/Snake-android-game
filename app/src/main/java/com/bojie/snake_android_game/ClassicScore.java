package com.bojie.snake_android_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassicScore extends AppCompatActivity {

    private TextView scoreTextView;
    private Animation mAnimation;
    private TextView mHighScoreTextView;
    private ImageView playAgainImageView;
    private ImageView mainMenuImageView;
    private TextView gameOverTitleLeftTextView; = (TextView) findViewById(R.id.gameover_left);
    private TextView gameOverTitleMiddleTextView; = (TextView) findViewById(R.id.gameover_middle);
    private TextView gameOverTitleRightTextView; = (TextView) findViewById(R.id.gameover_right);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_classic_score);
    }

    private void initScore() {
        scoreTextView = (TextView) findViewById(R.id.player_score);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_for_classic_button);
        mAnimation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                        GameSettings.KEY_SNAKE_PREFERENCES, Context.MODE_PRIVATE);
                int playerScore = preferences.getInt("Score", 0);
                scoreTextView.setText("Score: " + String.valueOf(playerScore));
                scoreTextView.setTextColor(Color.WHITE);
                scoreTextView.setGravity(Gravity.CENTER);
                scoreTextView.setBackgroundResource(R.mipmap.menu_options);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scoreTextView.startAnimation(mAnimation);
    }

    private void initHighScore() {
        mHighScoreTextView = (TextView) findViewById(R.id.mode_high_score);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_for_no_button);
        mAnimation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setHighScore();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mHighScoreTextView.startAnimation(mAnimation);

    }

    private void setHighScore() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                GameSettings.KEY_SNAKE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int highScore = preferences.getInt("HighScoreClassic", 0);
        int lastScore = preferences.getInt("Score", 0);
        if (lastScore > highScore) {
            editor.putInt("HighScoreClassic", lastScore);
            editor.apply();
            highScore = lastScore;
        }

        mHighScoreTextView.setText(String.valueOf(highScore));
        mHighScoreTextView.setTextColor(Color.WHITE);
        mHighScoreTextView.setGravity(Gravity.CENTER);
        mHighScoreTextView.setBackgroundResource(R.mipmap.menu_options);
    }

    private void initPlayAgain() {
        playAgainImageView = (ImageView) findViewById(R.id.play_again);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_for_settings_button);
        mAnimation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playAgainImageView.setImageResource(R.mipmap.again);
                playAgainImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentClassic = new Intent(ClassicScore.this, ClassicSnake.class);
                        intentClassic.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentClassic);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        playAgainImageView.startAnimation(mAnimation);

    }

    private void initMainMenu() {
        mainMenuImageView = (ImageView) findViewById(R.id.goto_main_menu);
        mAnimation = AnimationUtils.loadAnimation(ClassicSnake.this, R.anim.anim_for_bomb_button);
        mAnimation.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mainMenuImageView.setImageResource(R.mipmap.menu);
                mainMenuImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        scoreTextView.setBackgroundResource(R.mipmap.menu_options);
                        scoreTextView.setText("");
                        scoreTextView.setTextColor(Color.BLACK);
                        mHighScoreTextView.setBackgroundResource(R.mipmap.menu_options);
                        mHighScoreTextView.setText("");
                        mHighScoreTextView.setTextColor(Color.BLACK);

                        playAgainImageView.setBackgroundResource(R.mipmap.menu_options);
                        mainMenuImageView.setBackgroundResource(R.mipmap.menu_options);

                        // Reverse animate
                        Animation animationScore = AnimationUtils.loadAnimation(ClassicScore.this,
                                R.anim.reverse_for_classic_button);
                        animationScore.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animationHighScore = AnimationUtils.loadAnimation(ClassicScore.this,
                                R.anim.reverse_for_no_button);
                        animationHighScore.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animationPlayAgain = AnimationUtils.loadAnimation(ClassicScore.this,
                                R.anim.reverse_for_settings_button);
                        animationPlayAgain.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animationMainMenu = AnimationUtils.loadAnimation(ClassicScore.this,
                                R.anim.reverse_for_bomb_button);
                        animationMainMenu.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animationTitleLeft = AnimationUtils.loadAnimation(ClassicScore.this,
                                R.anim.anim_for_title_left);
                        animationTitleLeft.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        Animation animationTitleMiddle = AnimationUtils.loadAnimation(ClassicScore.this,
                                R.anim.anim_for_title_middle);
                        animationTitleMiddle.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        Animation animationTitleRight = AnimationUtils.loadAnimation(ClassicScore.this,
                                R.anim.anim_for_title_right);
                        animationTitleRight.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);



                        scoreTextView.startAnimation(animationScore);
                        mHighScoreTextView.startAnimation(animationHighScore);
                        playAgainImageView.startAnimation(animationPlayAgain);
                        mainMenuImageView.startAnimation(animationMainMenu);
                        gameOverTitleLeftTextView.startAnimation(animationTitleLeft);
                        gameOverTitleMiddleTextView.startAnimation(animationTitleMiddle);
                        gameOverTitleRightTextView.startAnimation(animationTitleRight);

                        Handler myHander = new Handler();
                        myHander.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intentMain = new Intent(ClassicScore.this, MainMenu.class);
                                intentMain.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intentMain);
                            }
                        }, GameSettings.START_NEW_ACTIVITY_DURATION);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initTitle() {
        gameOverTitleLeftTextView = (TextView) findViewById(R.id.gameover_left);
        gameOverTitleMiddleTextView = (TextView) findViewById(R.id.gameover_middle);
        gameOverTitleRightTextView = (TextView) findViewById(R.id.gameover_right);

        Animation animationTitleLeft = AnimationUtils.loadAnimation(ClassicScore.this,
                R.anim.back_anim_for_title_left);
        animationTitleLeft.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        animationTitleLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gameOverTitleLeftTextView.startAnimation(animationTitleLeft);


        Animation animationTitleMiddle = AnimationUtils.loadAnimation(ClassicScore.this,
                R.anim.back_anim_for_title_middle);
        animationTitleMiddle.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        animationTitleMiddle.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gameOverTitleMiddleTextView.startAnimation(animationTitleLeft);

        Animation animationTitleRight = AnimationUtils.loadAnimation(ClassicScore.this,
                R.anim.back_anim_for_title_right);
        animationTitleRight.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        animationTitleRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gameOverTitleRightTextView.startAnimation(animationTitleLeft);
    }

}
