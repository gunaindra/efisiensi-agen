package com.busefisensi.efisiensiagen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Adapter.OnSeatSelected;
import com.busefisensi.efisiensiagen.Adapter.SeatAdapter;
import com.busefisensi.efisiensiagen.Constant.URL;
import com.busefisensi.efisiensiagen.Database.SharedPrefUser;
import com.busefisensi.efisiensiagen.Model.Passenger;
import com.busefisensi.efisiensiagen.Model.Schedule;
import com.busefisensi.efisiensiagen.Model.Seat;
import com.busefisensi.efisiensiagen.Parser.Parser;
import com.busefisensi.efisiensiagen.Transport.HTTPClient;
import com.javasoul.swframework.component.SWProgressDialog;
import com.javasoul.swframework.component.SWToast;
import com.javasoul.swframework.model.SWResult;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookingSeatActivity extends AppCompatActivity implements OnSeatSelected {


    //    private GridLayoutManager lLayout;
    private List<Seat> seats = new ArrayList<>();
    private Seat seat = new Seat();
    private RecyclerView rvSeat;
    private GridLayoutManager llayout;
    TextView jumlahPenumpang;
    TextView kodeBus;
    ImageView ivSeat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_seat);
        Intent intent = getIntent();
        String passenger = intent.getStringExtra("jumlahPenumpang");
        final int intPassenger = Integer.parseInt(passenger);
        Log.d("penumpang", "jumlah" + Integer.parseInt(passenger));
        kodeBus = findViewById(R.id.tvKodebus);
        kodeBus.setText("Kode Bus " + intent.getStringExtra("jadwal"));
        jumlahPenumpang = findViewById(R.id.tvJmlPenumpang);
        jumlahPenumpang.setText("Jumlah Penumpang : " + intPassenger);
        ivSeat = findViewById(R.id.ivSeat);
        final ScrollView scrollView = findViewById(R.id.svSeat);
        scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int limitClick = 0;
                limitClick++;
                if (limitClick >= intPassenger){
                    scrollView.setClickable(false);
                    scrollView.setEnabled(false);
                }

            }
        });

        loadAllSeat();
    }

    private void loadAllSeat(){
        final SWProgressDialog progressDialog = new SWProgressDialog(this);
        progressDialog.showProgressIndeterminate(true);

        Intent intent = getIntent();
        ArrayList<Passenger> passenger = intent.getParcelableArrayListExtra("passengers");
        String idJadwal = intent.getStringExtra("schedule");
        String url = URL.SEAT.get() + idJadwal + "/seating";
        Log.d("URLnya", url);

        SharedPrefUser sharedPrefUser = new SharedPrefUser(getApplicationContext());
        String token = sharedPrefUser.getToken();


        HTTPClient.sendHTTPGETJSON(token, url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Response res = response;
                Parser parser = new Parser(res);

                final SWResult result = parser.seatParser();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.getResult()) {
                            HashMap<String, Object> data = result.getData();

                            seats = (List) data.get("seats");

//                            SWToast.showLongSuccess("Seat has been loaded");
                            llayout = new GridLayoutManager(BookingSeatActivity.this, seats.size() / 5, GridLayoutManager.HORIZONTAL, true);
                            rvSeat = findViewById(R.id.rv_seat);
                            rvSeat.setLayoutManager(llayout);



                            SeatAdapter seatAdapter = new SeatAdapter(BookingSeatActivity.this, seats);
                            rvSeat.setAdapter(seatAdapter);
                        }else {
//                            SWToast.showLongError(result.getError());
                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    private void onActivityResult(@Nullable Intent data){}

    @Override
    public void onSeatSelected(int count) {

        SWToast.showShortInfo("Memilih " + count + " kursi" );
    }
}

