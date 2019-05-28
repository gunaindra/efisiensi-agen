package com.busefisensi.efisiensiagen.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefUser {

    private static final String PREFS_NAME = "sharedpreferenceuser";

    static SharedPreferences sp;
    static SharedPreferences.Editor prefEditor = null;

    public SharedPrefUser(Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setNama(String nama){
        sp.edit().putString("nama", nama).apply();
    }

    public String getNama(){
        String nama = sp.getString("nama", "");
        return nama;
    }

    public void setNip(String nip){
        sp.edit().putString("nip", nip).apply();
    }

    public String getNip(){
        String nip = sp.getString("nip", "");
        return nip;
    }

    public void setAgen(String agen){
        sp.edit().putString("agen", agen).apply();
    }

    public String getAgen(){
        String agen = sp.getString("agen", "");
        return agen;
    }

    public void setToken(String token){
        sp.edit().putString("token", token).apply();
    }

    public String getToken(){
        String token = sp.getString("token", "");
        return token;
    }

    public void setIsLogin(boolean value){
        sp.edit().putBoolean("isLogin", value).apply();
    }

    public Boolean getIsLogin(){
        Boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
}
