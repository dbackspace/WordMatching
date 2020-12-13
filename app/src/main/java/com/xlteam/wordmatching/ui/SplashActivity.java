package com.xlteam.wordmatching.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xlteam.wordmatching.ui.menu.MenuActivity;
import com.xlteam.wordmatching.ui.playscreen.PlayActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
