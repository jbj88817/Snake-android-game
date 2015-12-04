package com.bojie.snake_android_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainMenu extends AppCompatActivity {

    private RelativeLayout mSnakeLayout;
    private Animation mCompileAnim;
    private AdView mAdView;
    private ImageView mClassicBtn;
    private ImageView mNoWallBtn;
    private ImageView mBombBtn;
    private TextView mTitleLeft;
    private TextView mTitleMiddle;
    private TextView mTitleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        mSnakeLayout = (RelativeLayout) findViewById(R.id.snake_layout);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(GameSettings.MY_AD_UNIT_ID);
        mSnakeLayout.addView(mAdView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);
    }

    private void initClassic() {
        mClassicBtn = (ImageView) findViewById(R.id.classic);
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_classic_button);
        mCompileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mCompileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mClassicBtn.setImageResource(R.mipmap.classic);
                mClassicBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentClassic = new Intent(MainMenu.this, ClassicSnake.class);
                        intentClassic.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentClassic);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mClassicBtn.startAnimation(mCompileAnim);
    }

    private void initNoWalls() {
        mNoWallBtn = (ImageView) findViewById(R.id.no_walls);
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_no_button);
        mCompileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mCompileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mNoWallBtn.setImageResource(R.mipmap.no_walls);
                mNoWallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentNoWalls = new Intent(MainMenu.this, NoWallsSnake.class);
                        intentNoWalls.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentNoWalls);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mNoWallBtn.startAnimation(mCompileAnim);
    }


    private void initBomb() {
        mBombBtn = (ImageView) findViewById(R.id.gamecenter);
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_gamecenter_button);
        mCompileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mCompileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBombBtn.setImageResource(R.mipmap.bombsnake);
                mBombBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bombSnakeIntent = new Intent(MainMenu.this, BombSnake.class);
                        bombSnakeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(bombSnakeIntent);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mBombBtn.startAnimation(mCompileAnim);
    }

    private void initTitle() {
        mTitleLeft = (TextView) findViewById(R.id.snake_left);
        mTitleMiddle = (TextView) findViewById(R.id.snake_middle);
        mTitleLeft = (TextView) findViewById(R.id.snake_right);

        // Set up anim for title left
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_for_title_left);
        mCompileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        mCompileAnim.setAnimationListener(new Animation.AnimationListener() {
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

        mTitleLeft.startAnimation(mCompileAnim);


        // Set up anim for title middle
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_for_title_middle);
        mCompileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        mCompileAnim.setAnimationListener(new Animation.AnimationListener() {
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

        mTitleMiddle.startAnimation(mCompileAnim);

        // Set up anim for title right
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_for_title_right);
        mCompileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        mCompileAnim.setAnimationListener(new Animation.AnimationListener() {
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

        mTitleRight.startAnimation(mCompileAnim);
    }

}
