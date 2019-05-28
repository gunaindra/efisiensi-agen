package com.busefisensi.efisiensiagen.Parser;

import com.busefisensi.efisiensiagen.Model.Deposit;
import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DepositParser {

    private SWResult result = new SWResult();

    public DepositParser(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);




            HashMap<String, Object> data = new HashMap<>();
//            data.put("deposit", deposit.setDeposit(jsonObject.getString("deposit")));

            result.setResult(true);
            result.setData(data);
        }catch (JSONException e){
            result.setResult(false);
            result.addError(e.getMessage());

            SWLog.e(DepositParser.class, e.getMessage());
        }
    }

    public SWResult getResult(){
        return result;
    }
}
