package com.busefisensi.efisiensiagen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Model.Schedule;
import com.busefisensi.efisiensiagen.R;
import com.busefisensi.efisiensiagen.Util.StringUtil;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private Context context;
    private List<Schedule> schedules;
    private OnChooseSchedule onChooseSchedule;

    public ScheduleAdapter(Context context, List<Schedule> schedules, OnChooseSchedule onChooseSchedule){
        this.context = context;
        this.schedules = schedules;
        this.onChooseSchedule = onChooseSchedule;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_schedule_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i){
        final Schedule schedule = schedules.get(i);

        viewHolder.tvKodeBus.setText(schedule.getSchedule());
        viewHolder.tvJamKeberangkatan.setText("Keberangkatan Pukul " + schedule.getUpTime());
        viewHolder.tvKursi.setText("Tersedia " + schedule.getEmptyChairs() + " kursi");
        viewHolder.tvPrice.setText(StringUtil.getPriceInRupiahFormat(schedule.getPrice()));

        viewHolder.cvSchedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onChooseSchedule.chooseSchedule(schedule);
            }
        });
    }

    @Override
    public int getItemCount(){
        return schedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvKodeBus;
        private TextView tvJamKeberangkatan;
        private TextView tvKursi;
        private TextView tvPrice;
        private CardView cvSchedule;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvKodeBus = itemView.findViewById(R.id.tvKodeBus);
            tvJamKeberangkatan = itemView.findViewById(R.id.tvJamBerangkat);
            tvKursi = itemView.findViewById(R.id.tvKursi);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            cvSchedule = itemView.findViewById(R.id.cvSchedule);
        }
    }

    public interface OnChooseSchedule {
        void chooseSchedule(Schedule schedule);
    }
}
