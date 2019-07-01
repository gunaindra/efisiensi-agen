package com.busefisensi.efisiensiagen.Parser;

import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class Parser {

    Response response;
    String responseBody;

    public Parser(Response response){
        this.response = response;
    }

    public SWResult depositParser() {
        SWResult result = defaultParser();
        if (result.getResult()){
            DepositParser depositParser = new DepositParser(responseBody);
            return depositParser.getResult();
        }

        return result;
    }

    public SWResult agentParser() {
        SWResult result = defaultParser();
        if(result.getResult()) {
            AgentParser agentParser = new AgentParser(responseBody);
            return agentParser.getResult();
        }

        return result;
    }

    public SWResult scheduleParser() {
        SWResult result = defaultParser();
        if(result.getResult()) {
            ScheduleParser scheduleParser = new ScheduleParser(responseBody);
            return scheduleParser.getResult();
        }

        return result;
    }

    public SWResult seatParser(){
        SWResult result = defaultParser();
        if(result.getResult()){
            SeatParser seatParser = new SeatParser(responseBody);
            return seatParser.getResult();
        }
        return result;
    }

    private SWResult defaultParser() {
        try {
            int code = response.code();
            responseBody = response.body().string();

            if(code == 200) {
                JSONObject jsonObject = new JSONObject(responseBody);
                Boolean success = jsonObject.getBoolean("success");
                String message = jsonObject.getString("message");

                return new SWResult(success, message);
            } else if(code == 500) {
                return new SWResult(false, "Server busy");
            } else if(code == 404) {
                return new SWResult(false, "Source not found");
            } else if (code == 502) {
                return new SWResult(false, "Bad gateway");
            } else {
                return new SWResult(false, "Unhandled Exception");
            }
        } catch(JSONException e) {
            SWLog.e(Parser.class, "defaultParser", e.getMessage());
            return new SWResult(false, e.getMessage());
        } catch(IOException e) {
            SWLog.e(Parser.class, "defaultParser", e.getMessage());
            return new SWResult(false, e.getMessage());
        }
    }
}
