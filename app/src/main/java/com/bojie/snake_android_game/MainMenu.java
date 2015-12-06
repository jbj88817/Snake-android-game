package com.bojie.snake_android_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
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
    private ImageView mSettingsBtn;

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

        initClassic();
        initNoWalls();
        initBomb();
        initTitle();
        initSettings();
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
        mBombBtn = (ImageView) findViewById(R.id.bomb);
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_bomb_button);
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
        mTitleRight = (TextView) findViewById(R.id.snake_right);

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

    private void initSettings() {
        mSettingsBtn = (ImageView) findViewById(R.id.settings);
        mCompileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_settings_button);
        mCompileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);
        mCompileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSettingsBtn.setImageResource(R.mipmap.settings);
                mSettingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        mSettingsBtn.setImageResource(R.mipmap.menu_options);
                        mClassicBtn.setImageResource(R.mipmap.menu_options);
                        mNoWallBtn.setImageResource(R.mipmap.menu_options);
                        mBombBtn.setImageResource(R.mipmap.menu_options);

                        Animation animation = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_classic_button);
                        animation.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation2 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_no_button);
                        animation2.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation3 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_bomb_button);
                        animation3.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);
                        Animation animation4 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_settings_button);
                        animation4.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        Animation animationTitleLeft = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_left);
                        animationTitleLeft.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animationTitleMeddle = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_middle);
                        animationTitleMeddle.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);
                        Animation animationTitleRight = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_right);
                        animationTitleRight.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        mClassicBtn.startAnimation(animation);
                        mNoWallBtn.startAnimation(animation2);
                        mBombBtn.startAnimation(animation3);
                        mSettingsBtn.startAnimation(animation4);
                        mTitleLeft.startAnimation(animationTitleLeft);
                        mTitleMiddle.startAnimation(animationTitleMeddle);
                        mTitleRight.startAnimation(animationTitleRight);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent settingsIntent = new Intent(MainMenu.this, Settings.class);
                                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(settingsIntent);
                            }
                        }, GameSettings.START_NEW_ACTIVITY_DURATION);
                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mSettingsBtn.setAnimation(mCompileAnim);
    }

}
