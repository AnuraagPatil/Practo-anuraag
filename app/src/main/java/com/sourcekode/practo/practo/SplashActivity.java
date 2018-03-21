package com.sourcekode.practo.practo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        sessionManager = new SessionManager(this);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        if (sessionManager.getFirstTime()) {
            Intent mainIntent = new Intent(SplashActivity.this, SignIn.class);
            startActivity(mainIntent);
            finish();

        } else {
            Intent mainIntent = new Intent(SplashActivity.this, DrawerNavigationActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
