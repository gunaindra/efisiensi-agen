package com.busefisensi.efisiensiagen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Adapter.MenuAdapter;
import com.busefisensi.efisiensiagen.Adapter.MenuItemClickListener;
import com.busefisensi.efisiensiagen.BookingActivity;
import com.busefisensi.efisiensiagen.Constant.URL;
import com.busefisensi.efisiensiagen.Database.SharedPrefUser;
import com.busefisensi.efisiensiagen.Model.Deposit;
import com.busefisensi.efisiensiagen.Model.Menu;
import com.busefisensi.efisiensiagen.Parser.Parser;
import com.busefisensi.efisiensiagen.R;
import com.busefisensi.efisiensiagen.Transport.HTTPClient;
import com.busefisensi.efisiensiagen.Util.StringUtil;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class HomeFragment extends Fragment {

    private List<Menu> menus = new ArrayList<>();

    private GridLayoutManager lLayout;
    private List<Deposit> deposits = new ArrayList<>();
    String deposit;

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        List<Menu> menus = getAllMenus();
        lLayout = new GridLayoutManager(getContext(), 4);
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.rv_menu);
        recyclerView.setLayoutManager(lLayout);
        MenuAdapter menuAdapter = new MenuAdapter(getContext(), menus);
        recyclerView.setAdapter(menuAdapter);
        getDeposit();

        recyclerView.addOnItemTouchListener(
                new MenuItemClickListener(getContext(), recyclerView, new MenuItemClickListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view1, int position){
                        switch (position){
                            case 0:
                                Intent intent = new Intent(getContext(), BookingActivity.class);
                                startActivity(intent);
                                break;
                        }
                    }

                    @Override
                    public void onLongItemClick(View view1, int position){
                        Log.d("KlikPanjang", "ke" + position);
                    }
                })
        );


        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private List<Menu> getAllMenus(){
        List<Menu> menus = new ArrayList<Menu>();
        menus.add(new Menu(R.drawable.ticket, "eTicket"));
        menus.add(new Menu(R.drawable.truck, "Paket"));
        menus.add(new Menu(R.drawable.userclock, "Reschedule"));
        menus.add(new Menu(R.drawable.receipt, "Pembatalan"));
        menus.add(new Menu(R.drawable.list, "Manifest"));
        menus.add(new Menu(R.drawable.info, "Info"));

        return menus;
    }

    private void getDeposit(){
        SharedPrefUser sharedPrefUser = new SharedPrefUser(getContext());
        String token = sharedPrefUser.getToken().toString();
//        String url = URL.DEPOSIT;

       HTTPClient.sendHTTPGETJSON(token, URL.DEPOSIT.get(), new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               final String responseData = response.body().string();
               try {
                   JSONObject jsonObject = new JSONObject(responseData);
                   deposit = jsonObject.getString("deposit");
               }catch (JSONException e){
                   e.printStackTrace();
               }
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       TextView tvdeposit = getView().findViewById(R.id.tvDeposit);
                       tvdeposit.setText(StringUtil.getPriceInRupiahFormat(Long.parseLong(deposit)));
                   }
               });
           }
       });
    }
}
