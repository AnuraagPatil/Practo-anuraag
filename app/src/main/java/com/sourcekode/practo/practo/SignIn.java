package com.sourcekode.practo.practo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;

public class SignIn extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    public static final String LOGINED_NAME = "login_id";
    public static final String EMAIL_ID = "email_id";
    public static final String PROFILE_PIC = "profile_pic";
    private static final String TAG = "SIGNIN_EXERCISE";
    private static final int RES_CODE_SIGN_IN = 1001;
    static GoogleApiClient mGoogleApiClient;
    private static TextView m_tvStatus;
    SessionManager sessionManager;

    public static void signOut() {
        // TODO: Sign the user out and update the UI
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        m_tvStatus.setText(R.string.status_notsignedin);

                    }
                });
    }

    private void startSignIn() {
        // TODO: Create sign-in intent and begin auth flow
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RES_CODE_SIGN_IN);
    }

    private void signInResultHandler(GoogleSignInResult result) {

        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();

            sessionManager = new SessionManager(this);
            sessionManager.setFirsttime(true);

            try {
                Intent intent = new Intent(this, DrawerNavigationActivity.class);
                intent.putExtra(LOGINED_NAME, acct.getDisplayName());
                intent.putExtra(EMAIL_ID, acct.getEmail());
                intent.putExtra(PROFILE_PIC, acct.getPhotoUrl().toString());
                startActivity(intent);

                m_tvStatus.setText(R.string.status_signedin);

            } catch (NullPointerException e) {
                Log.d(TAG, "Error retrieving some account information");
            }

        } else {

            Status status = result.getStatus();
            int statusCode = status.getStatusCode();
            if (statusCode == GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                m_tvStatus.setText(R.string.status_signincancelled);
            } else if (statusCode == GoogleSignInStatusCodes.SIGN_IN_FAILED) {
                m_tvStatus.setText(R.string.status_signinfail);
            } else {
                m_tvStatus.setText(R.string.status_nullresult);
            }
        }
    }

    // *************************************************
    // -------- ANDROID ACTIVITY LIFECYCLE METHODS
    // *************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        m_tvStatus = (TextView) findViewById(R.id.tvStatus);

        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnSignOut).setOnClickListener(this);

        // TODO: Create a sign-in options object
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();

        // Build the GoogleApiClient object
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // TODO: Customize the sign in button
        SignInButton signInButton = (SignInButton) findViewById(R.id.btnSignIn);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setColorScheme(SignInButton.COLOR_DARK);
        signInButton.setScopes(gso.getScopeArray());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RES_CODE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            signInResultHandler(result);
        }
    }

    // *************************************************
    // -------- GOOGLE PLAY SERVICES METHODS
    // *************************************************
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Could not connect to Google Play Services");
    }

    // *************************************************
    // -------- CLICK LISTENER FOR THE ACTIVITY
    // *************************************************
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                startSignIn();
                break;
            case R.id.btnSignOut:
                signOut();
                break;
        }
    }


}
