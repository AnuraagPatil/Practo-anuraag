package com.sourcekode.practo.practo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sourcekode.practo.practo.SampleDataProvider.DataProvider;

public class Doctors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        String Speciality = getIntent().getStringExtra(RecyclerViewAdapter.SPECILITY_KEY);
        setTitle(Speciality);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview_doc_list);

        RecyclerViewAdapterForDocList myAdapter = new RecyclerViewAdapterForDocList(this, DataProvider.doctorsList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(myAdapter);

        Toast.makeText(this, "Speciality : "+Speciality, Toast.LENGTH_SHORT).show();
    }
}
