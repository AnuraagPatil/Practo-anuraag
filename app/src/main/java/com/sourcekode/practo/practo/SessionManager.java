package com.sourcekode.practo.practo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    public static final String TAG = "SessionManager";
    private static final String FIRSTTIME = "firsttime";
    private static final String PREF_NAME = "Practo";
    static SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Context mContext;
    SharedPreferences pref;

    public SessionManager(Context context) {
        mContext = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirsttime(boolean isLoggedIn) {
        editor.putBoolean(FIRSTTIME, isLoggedIn);
        editor.commit();
        Log.d(TAG, "setFirsttime() called with: isLoggedIn = [" + isLoggedIn + "]");
    }

    public boolean getFirstTime() {
        return pref.getBoolean(FIRSTTIME, false);
    }

}
