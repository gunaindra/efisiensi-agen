package com.busefisensi.efisiensiagen.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.busefisensi.efisiensiagen.Model.Passenger;
import com.busefisensi.efisiensiagen.Model.Seat;
import com.busefisensi.efisiensiagen.R;

import java.util.ArrayList;
import java.util.List;

public class SeatAdapter extends SelectableSeatAdapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Seat> seats;
    private OnSeatSelected mOnSeatSelected;


    public SeatAdapter(Context context, List<Seat> seats){
        mOnSeatSelected = (OnSeatSelected) context;
        this.context = context;
        this.seats = seats;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_seat_item,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position){
        Intent intent = ((Activity)context).getIntent();
        final String passenger = intent.getStringExtra("jumlahPenumpang");
        final ViewHolder holder = (ViewHolder) viewHolder;
        if(seats.get(position).getNomor().isEmpty()){
            holder.ivSeat.setVisibility(View.GONE);
        }else if(seats.get(position).getNomor().equals("supir")){
            holder.ivSeat.setImageResource(R.drawable.steer);
            holder.ivSeat.setClickable(false);
            holder.ivSeat.setEnabled(false);
        }else if (seats.get(position).getTersedia().equals("unavailable")){
            holder.ivSeat.setImageResource(R.drawable.rounded_et_orange_red);
            holder.ivSeat.setClickable(false);
            holder.ivSeat.setEnabled(false);
        }else {
            holder.ivSeat.setImageResource(seats.get(position).getPhoto());
            holder.ivSeat.setClickable(true);
            holder.ivSeat.setEnabled(true);
            holder.ivSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int limit =0;
                    if(limit == Integer.parseInt(passenger)) {
                        holder.ivSeat.getTag();
                        Log.d("limit", "seat" + limit);
                        holder.ivSeat.setClickable(false);
                        holder.ivSeat.setEnabled(false);

//                        mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                    }else{
                        toggleSelection(position);
                        holder.ivSeat.setClickable(true);
                        holder.ivSeat.setEnabled(true);
                        Toast.makeText(context, seats.get(position).getNomor(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

//            toggleSelection(position););
            holder.ivSeat.setImageResource(isSelected(position) ? R.drawable.rounded_et_grey : R.drawable.rounded_et_orange);

        }
    }

    @Override
    public int getItemCount(){
        return this.seats.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivSeat;

        private ViewHolder(@NonNull View itemView){
            super(itemView);
            ivSeat = itemView.findViewById(R.id.ivSeat);
        }
    }
}
