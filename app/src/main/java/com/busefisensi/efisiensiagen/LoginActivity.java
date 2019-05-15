package com.busefisensi.efisiensiagen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.busefisensi.efisiensiagen.Constant.URL;
import com.busefisensi.efisiensiagen.Database.SPUser;
import com.busefisensi.efisiensiagen.Model.User;
import com.busefisensi.efisiensiagen.Transport.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.StreamHandler;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();

        if(TextUtils.isEmpty(username)){
            et_username.setError("Silahkan masukan username Anda");
            et_password.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)){
            et_password.setError("Silahkan masukan password Anda");
            et_password.requestFocus();
            return;
        }

        class UserLogin extends AsyncTask<Void, Void, String>{

            ProgressBar progressBar;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                progressBar = findViewById(R.id.login_progress);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);

                try{
                    JSONObject obj = new JSONObject(s);

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();;

                        JSONObject userJson = obj.getJSONObject("user");

                        User user = new User(
                                userJson.getString("nama"),
                                userJson.getString("nip"),
                                userJson.getString("agen")
                        );

//                        List<User> users = new ArrayList<>();
//                        for (int i=0; i <userJson.length(); i++){
//                            JSONObject userObj = userJson.getJSONObject(i);
//                            User user = new User();
//                            user.setNama(userObj.getString("nama"));
//                            user.setNik(userObj.getString("nip"));
//                            user.setAgen(userObj.getString("agen"));
//                            users.add(user);
//                        }

                        SPUser.getInstance(getApplicationContext()).userLogin(user);

                        finish();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            protected String doInBackground(Void... voids){
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return requestHandler.sendPostRequest(URL.LOGINURL, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
}

