package com.busefisensi.efisiensiagen.Database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.busefisensi.efisiensiagen.LoginActivity;
import com.busefisensi.efisiensiagen.Model.User;

public class SPUser {

    private static final String SHARED_PREF_NAME = "usersharedpreferences";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_NAMA = "keynama";
    private static final String KEY_NIK = "keynik";
    private static final String KEY_AGEN = "keyagen";

    private static SPUser mInstance;
    private static Context mCtx;

    private SPUser(Context context){
        mCtx = context;
    }

    public static synchronized SPUser getInstance(Context context){
        if(mInstance == null){
            mInstance = new SPUser(context);
        }
        return mInstance;
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_NAMA, user.getNama());
        editor.putString(KEY_NIK, user.getNik());
        editor.putString(KEY_AGEN, user.getAgen());
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_NIK, null),
                sharedPreferences.getString(KEY_AGEN, null)
        );
    }

    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
