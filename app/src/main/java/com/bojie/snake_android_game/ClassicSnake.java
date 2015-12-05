package com.bojie.snake_android_game;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class ClassicSnake extends AppCompatActivity {

    private boolean mPlayMusic;
    private MediaPlayer mMusicPlayer;

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

}
