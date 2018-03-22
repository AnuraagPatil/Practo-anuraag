package com.sourcekode.practo.practo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.sourcekode.practo.practo.SampleDataProvider.DataProvider;
import com.sourcekode.practo.practo.util.ItemOffsetDecoration;

import java.io.IOException;

import static com.sourcekode.practo.practo.SignIn.EMAIL_ID;
import static com.sourcekode.practo.practo.SignIn.LOGINED_NAME;
import static com.sourcekode.practo.practo.SignIn.PROFILE_PIC;


public class DrawerNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "DrawerActivity";
    final Context context = this;

    GoogleApiClient mGoogleApiClient;
    SessionManager sessionManager;

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_navigation);

        sessionManager = new SessionManager(getApplicationContext());

        NavigationView navigationView = findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);
        TextView textView_name = header.findViewById(R.id.name);
        TextView textView_email = header.findViewById(R.id.email);
        ImageView imageView = header.findViewById(R.id.profile_image);


        navigationView.setNavigationItemSelectedListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String userName = getIntent().getStringExtra(LOGINED_NAME);
        String email = getIntent().getStringExtra(EMAIL_ID);

        String uriString = getIntent().getStringExtra(PROFILE_PIC);

        if (uriString.isEmpty()) {

            Toast.makeText(context, "Image not found", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        } else {

            Uri uri = Uri.parse(uriString);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        textView_name.setText(userName);
        textView_email.setText(email);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, DataProvider.specialities);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setTitle("Thanks for using this app");

            alertDialogBuilder.setMessage("Would you like to try more!!")
                    .setCancelable(false)
                    .setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.fav1) {
            // Handle the camera action
        } else if (id == R.id.fav2) {

        } else if (id == R.id.fav3) {

        } else if (id == R.id.fav4) {

        } else if (id == R.id.fav5) {

        } else if (id == R.id.share) {

        } else if (id == R.id.about_us) {

        } else if (id == R.id.sign_out) {


            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // ...

                            sessionManager.setFirsttime(true);

                            Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
//
                            NavigationView navigationView = findViewById(R.id.nav_view);

                            View header = navigationView.getHeaderView(0);
                            TextView textView_name = header.findViewById(R.id.name);
                            TextView textView_email = header.findViewById(R.id.email);
                            ImageView imageView = header.findViewById(R.id.profile_image);

                            textView_name.setText("not signed in");
                            textView_email.setText("email");

                            item.setVisible(false);

                            Menu menu = navigationView.getMenu();
                            MenuItem signOut = menu.findItem(R.id.sign_in);
                            signOut.setVisible(true);

                        }
                    });

        } else if (id == R.id.sign_in) {

            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
