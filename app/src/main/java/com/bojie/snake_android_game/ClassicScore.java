package com.bojie.snake_android_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassicScore extends AppCompatActivity {

    private TextView scoreTextView;
    private Animation mAnimation;
    private TextView mHighScoreTextView;
    private ImageView playAgainImageView;
    private TextView playAgainTextView;

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

}
