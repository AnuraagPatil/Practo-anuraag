package com.sourcekode.practo.practo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public static final String LOGINED_NAME = "Name";
    SessionManager sessionManager;

    private static final String TAG = "SIGNIN_EXERCISE";
    private static final int RES_CODE_SIGN_IN = 1001;

    private GoogleApiClient mGoogleApiClient;

    private TextView m_tvStatus;
    private TextView m_tvDispName;
    private TextView m_tvEmail;

    private void startSignIn() {
        // TODO: Create sign-in intent and begin auth flow

        Intent intent = new Intent(this, DrawerNavigationActivity.class);
//        intent.putExtra(LOGINED_NAME, acct.getDisplayName());
        intent.putExtra(LOGINED_NAME, "Login Name");
        startActivity(intent);

//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RES_CODE_SIGN_IN);

    }

    private void signOut() {
        // TODO: Sign the user out and update the UI
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        m_tvStatus.setText(R.string.status_notsignedin);
                        m_tvEmail.setText("");
                        m_tvDispName.setText("");
                    }
                });
    }

    private void disconnect() {
        // TODO: Disconnect this account completely and update UI
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        m_tvStatus.setText(R.string.status_notconnected);
                        m_tvEmail.setText("");
                        m_tvDispName.setText("");
                    }
                });
    }

    private void signInResultHandler(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            m_tvStatus.setText(R.string.status_signedin);

            sessionManager = new SessionManager(this);
            sessionManager.setFirsttime(true);

            try {
                m_tvDispName.setText(acct.getDisplayName());
                m_tvEmail.setText(acct.getEmail());

                Intent intent = new Intent(this, DrawerNavigationActivity.class);
                intent.putExtra(LOGINED_NAME, acct.getDisplayName());
                startActivity(intent);

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
        m_tvDispName = (TextView) findViewById(R.id.tvDispName);
        m_tvEmail = (TextView) findViewById(R.id.tvEmail);

        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnSignOut).setOnClickListener(this);
        findViewById(R.id.btnDisconnect).setOnClickListener(this);

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
            case R.id.btnDisconnect:
                disconnect();
                break;
        }
    }
}

