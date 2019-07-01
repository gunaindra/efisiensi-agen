package com.busefisensi.efisiensiagen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Adapter.ScheduleAdapter;
import com.busefisensi.efisiensiagen.Constant.URL;
import com.busefisensi.efisiensiagen.Database.SharedPrefUser;
import com.busefisensi.efisiensiagen.Model.Agent;
import com.busefisensi.efisiensiagen.Model.Schedule;
import com.busefisensi.efisiensiagen.Parser.Parser;
import com.busefisensi.efisiensiagen.Transport.HTTPClient;
import com.javasoul.swframework.component.SWProgressDialog;
import com.javasoul.swframework.component.SWToast;
import com.javasoul.swframework.model.SWResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.OnChooseSchedule{

    TextView tvLokasiKeberangkatan;
    TextView tvTanggalKeberangkatan;

    private RecyclerView rvSchedule;
    private String date = "";
    private String agentOriginName;
    private String agentOrigin;
    private String agentDestination;
    private String dateOriginal;

    private List<Schedule> schedules = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_schedule);

        tvLokasiKeberangkatan = findViewById(R.id.tvLokasiKeberangkatan);
        tvTanggalKeberangkatan = findViewById(R.id.tvTglKeberangkatan);
        rvSchedule = findViewById(R.id.rv_city);

        Intent intent = getIntent();
        date = intent.getStringExtra("datebeautify");
        dateOriginal = intent.getStringExtra("date");
        agentOriginName = intent.getStringExtra("agentOriginName");
        agentOrigin = intent.getStringExtra("agentOrigin");
        agentDestination = intent.getStringExtra("agentDestination");
        tvLokasiKeberangkatan.setText("Keberangkatan " + agentOriginName);
        tvTanggalKeberangkatan.setText(date);


        loadSchedule();
    }

    private void loadSchedule(){
        StringBuilder builder = new StringBuilder();
        builder.append(URL.SCHEDULE.get());
        builder.append(agentOrigin + "/");
        builder.append(agentDestination + "/");
        builder.append(dateOriginal);

        SharedPrefUser sharedPrefUser = new SharedPrefUser(getApplicationContext());
        String token = sharedPrefUser.getToken();

        String url = builder.toString();
        Log.d("URL", "urlnya" + url);
        final SWProgressDialog progressDialog = new SWProgressDialog(this);
        progressDialog.showProgressIndeterminate(true);

        HTTPClient.sendHTTPGETJSON(token, url, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SWToast.showLongError(e.getMessage());
                        progressDialog.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Parser parser = new Parser(response);
                final SWResult result = parser.scheduleParser();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.getResult()){
//                            SWToast.showShortSuccess("Schedules has been loaded");

                            HashMap<String, Object> data = result.getData();

                            schedules = (List) data.get("schedules");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ScheduleActivity.this);
                            ScheduleAdapter scheduleAdapter = new ScheduleAdapter(ScheduleActivity.this, schedules, ScheduleActivity.this);

                            rvSchedule.setLayoutManager(linearLayoutManager);
                            rvSchedule.setAdapter(scheduleAdapter);
                        }else {
//                            SWToast.showLongError(result.getError());
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void chooseSchedule(Schedule schedule){
        Intent intent = new Intent();
        intent.putExtra("schedule", schedule);
        setResult(RESULT_OK, intent);
        finish();
    }
}
