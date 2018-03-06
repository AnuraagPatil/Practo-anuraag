package com.sourcekode.practo.practo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Doctors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        String Speciality = getIntent().getStringExtra(RecyclerViewAdapter.SPECILITY_KEY);
        setTitle(Speciality);

        Toast.makeText(this, "Speciality : "+Speciality, Toast.LENGTH_SHORT).show();
    }
}
