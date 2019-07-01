package com.busefisensi.efisiensiagen.Parser;

import com.busefisensi.efisiensiagen.Model.Schedule;
import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleParser {
    private SWResult result = new SWResult();

    public ScheduleParser(String json) {
        try {

            // FIXME hardcode gara2 waktu API
            JSONObject jsonObject = new JSONObject(json);
            JSONArray scheduleArray = jsonObject.getJSONArray("jadwal");
            List<Schedule> schedules = new ArrayList<>();

            for(int i=0; i<scheduleArray.length(); i++) {
                JSONObject object = scheduleArray.getJSONObject(i);
                Schedule schedule = new Schedule();
                schedule.setId(object.getInt("id"));
                schedule.setSchedule(object.getString("jadwal"));
                schedule.setDepartureTime(object.getString("waktuBerangkat"));
                schedule.setArriveTime(object.getString("waktuSampai"));
                schedule.setUpTime(object.getString("waktuNaik"));
                schedule.setDownTime(object.getString("waktuTurun"));
                schedule.setPrice(object.getLong("tarif"));
                schedule.setEmptyChairs(object.getInt("jumlahKursiKosong"));
                schedule.setBusType(object.getString("tipeBus"));

                schedules.add(schedule);
            }

            HashMap<String, Object> data = new HashMap<>();
            data.put("schedules", schedules);

            result.setResult(true);
            result.setData(data);

        } catch(JSONException e) {
            result.setResult(false);
            result.addError(e.getMessage());

            SWLog.e(AgentParser.class, e.getMessage());
        }
    }

    public SWResult getResult() {
        return result;
    }
}

