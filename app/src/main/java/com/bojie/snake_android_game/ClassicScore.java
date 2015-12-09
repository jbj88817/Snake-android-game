package com.bojie.snake_android_game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ClassicScore extends AppCompatActivity {

    private TextView scoreTextView;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_score);
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

}
