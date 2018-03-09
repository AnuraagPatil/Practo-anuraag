package com.sourcekode.practo.practo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    int PRIVATE_MODE = 0;
    public static final String TAG = "SessionManager";
    private static final String FIRSTTIME = "firsttime";
    private static final String PREF_NAME = "Practo";

    Context mContext;
    SharedPreferences pref;
    static SharedPreferences.Editor editor;

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
