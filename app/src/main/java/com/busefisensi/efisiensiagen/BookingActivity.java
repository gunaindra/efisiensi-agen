package com.busefisensi.efisiensiagen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Constant.RequestCode;
import com.busefisensi.efisiensiagen.Constant.URL;
import com.busefisensi.efisiensiagen.Database.SharedPrefUser;
import com.busefisensi.efisiensiagen.Model.Agent;
import com.busefisensi.efisiensiagen.Transport.HTTPClient;
import com.busefisensi.efisiensiagen.Util.StringUtil;
import com.javasoul.swframework.component.SWDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookingActivity extends AppCompatActivity {
    private String kota;
    private Integer idKota;
    private EditText etKeberangkatan;
    private Button btnKeberangkatan;
    private Button btnTujuan;
    private Button btnTglKeberangkatan;
    private Button btnNextBook;
    private EditText etJumlahPemesan;
    private Agent agentDeparture;
    private Agent agentDestination;
    private String day;
    private String date;
    private String dateBeautify;
    private String hour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        btnKeberangkatan = findViewById(R.id.btn_keberangkatan);
        btnTujuan = findViewById(R.id.btn_tujuan);
        btnTglKeberangkatan = findViewById(R.id.btn_tglPemesanan);
        btnNextBook = findViewById(R.id.btn_nextbook);

        etJumlahPemesan = findViewById(R.id.et_jumlahPemesan);
        getKota();
        actions();
    }

    private void getKota() {
        SharedPrefUser sharedPrefUser = new SharedPrefUser(getApplicationContext());
//        String url = URL.AGEN;
        String token = sharedPrefUser.getToken();

        HTTPClient.sendHTTPGETJSON(token, URL.AGEN.get(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject jsonAgen = jsonObject.getJSONObject("agen");
                    idKota = jsonAgen.getInt("id");
                    kota = jsonAgen.getString("nama");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<Agent>  agents = new ArrayList<>();
                        Agent agent = new Agent();
                        agent.setId(idKota);
                        agents.add(agent);
                        HashMap<String, Object> data = new HashMap<>();
                        data.put("agents", agents);
                        btnKeberangkatan.setText(kota);

                    }
                });

            }
        });

    }

    private void actions() {
        btnKeberangkatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AgentActivity.class);
                intent.putExtra("requestCode", RequestCode.CHOOSE_DEPARTURE.get());
                intent.putExtra("namaAgen", kota);
                startActivityForResult(intent, RequestCode.CHOOSE_DEPARTURE.get());
            }
        });

        btnTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(agentDeparture)) {
                    Intent intent = new Intent(getBaseContext(), AgentActivity.class);
                    intent.putExtra("requestCode", RequestCode.CHOOSE_DESTINATION.get());
                    intent.putExtra("agentOrigin", agentDeparture.getId().toString());
                    intent.putExtra("namaAgen", kota);
                    startActivityForResult(intent, RequestCode.CHOOSE_DESTINATION.get());
                }else{
                    Intent intent = new Intent(getBaseContext(), AgentActivity.class);
                    intent.putExtra("requestCode", RequestCode.CHOOSE_DESTINATION.get());
                    intent.putExtra("agentOrigin", idKota.toString());
                    intent.putExtra("namaAgen", kota);
                    startActivityForResult(intent, RequestCode.CHOOSE_DESTINATION.get());
                }
            }
        });

        btnTglKeberangkatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(agentDestination) && btnKeberangkatan.getText() != null) {
                    startActivityForResult(new Intent(getBaseContext(), CalendarActivity.class), RequestCode.CHOOSE_DATE.get());
                } else {
                    SWDialog.warning(BookingActivity.this, "Validation", "Pilih Kota Asal dan Tujuan Terlebih Dahulu");
                }
            }
        });

        btnNextBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(agentDestination, date) && etJumlahPemesan.getText() != null){
                    Intent intent = new Intent();
                }
                else {
                    SWDialog.warning(BookingActivity.this, "Validation", "Pastikan Kota Keberangkatan, Kota Tujuan dan Tanggal sudah dipilih");
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.CHOOSE_DEPARTURE.get() && data != null) {
            Agent agent = data.getParcelableExtra("agent");
            if (agent != null) {
                agentDeparture = agent;
                btnKeberangkatan.setText(agent.getAgentName());
            }
        } else if (requestCode == RequestCode.CHOOSE_DESTINATION.get() && data != null) {
            Agent agent = data.getParcelableExtra("agent");

            if (agent != null) {
                agentDestination = agent;
                btnTujuan.setText(agent.getAgentName());
            }
        } else if (requestCode == RequestCode.CHOOSE_DATE.get() && data != null) {
            date = data.getStringExtra("fullDate");
            int date = data.getIntExtra("date", 0);
            String month = data.getStringExtra("month");
            int year = data.getIntExtra("year", 0);
            day = data.getStringExtra("day");
            dateBeautify = date + " " + month.substring(0, 3) + " " + year;
            btnTglKeberangkatan.setText(dateBeautify);
        }
    }

    private Boolean validate(Object... objects) {
        Boolean valid = true;
        for (Object object : objects) {
            if (object == null) {
                valid = false;
            }
        }

        return valid;
    }
}
