package com.sourcekode.practo.practo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import static com.sourcekode.practo.practo.SignIn.LOGINED_NAME;
import static com.sourcekode.practo.practo.SignIn.PROFILE_PIC;

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

                    if (sessionManager.getFirstTime()) {
                        Intent mainIntent = new Intent(SplashActivity.this, SignIn.class);
                        startActivity(mainIntent);

                    } else {
                        Intent mainIntent = new Intent(SplashActivity.this, DrawerNavigationActivity.class);
                        mainIntent.putExtra(LOGINED_NAME, "name");
                        mainIntent.putExtra(SignIn.EMAIL_ID, "email");
                        mainIntent.putExtra(PROFILE_PIC, "");
                        startActivity(mainIntent);
                    }

                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

}


