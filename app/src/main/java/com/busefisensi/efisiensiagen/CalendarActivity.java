package com.busefisensi.efisiensiagen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Util.DateUtil;
import com.javasoul.swframework.utils.SWDateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.DayOfWeek;

import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvTodayDate;
    private MaterialCalendarView calendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_calendar);

        tvTodayDate = findViewById(R.id.tv_date);
        calendarView = findViewById(R.id.calendar_view);

        tvTodayDate.setText(DateUtil.dateToStringDefault(new Date()));

        setupCalendar();
    }

    private void setupCalendar() {
        calendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.MONDAY)
                .setMinimumDate(CalendarDay.from(SWDateUtil.getYearNow(), SWDateUtil.getMonthNow()+1, SWDateUtil.getDayNow()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                Intent intent = new Intent();
                intent.putExtra("fullDate", calendarDay.getDate().toString());
                intent.putExtra("day", calendarDay.getDate().getDayOfWeek().name());
                intent.putExtra("date", calendarDay.getDate().getDayOfMonth());
                intent.putExtra("month", calendarDay.getDate().getMonth().name());
                intent.putExtra("year", calendarDay.getDate().getYear());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
