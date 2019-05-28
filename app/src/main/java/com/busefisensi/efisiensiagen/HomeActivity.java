package com.busefisensi.efisiensiagen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.busefisensi.efisiensiagen.Constant.URL;
import com.busefisensi.efisiensiagen.Database.SharedPrefUser;
import com.busefisensi.efisiensiagen.Fragment.HomeFragment;
import com.busefisensi.efisiensiagen.Transport.HTTPClient;
import com.busefisensi.efisiensiagen.Transport.RequestHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPrefUser sharedPrefUser = new SharedPrefUser(getApplicationContext());
        Log.d("Username", sharedPrefUser.getNama().toString());
        loadFragment(new HomeFragment());


    }

    private void loadFragment(Fragment fragment){
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

}
