package com.busefisensi.efisiensiagen.Parser;

import com.busefisensi.efisiensiagen.Model.Agent;
import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgentParser {

    private SWResult result = new SWResult();

    public AgentParser(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonAgen = jsonObject.getJSONArray("agen");

            List<Agent> agents = new ArrayList<>();
            for(int i=0; i<jsonAgen.length(); i++) {
                JSONObject agen = jsonAgen.getJSONObject(i);
                Agent agent = new Agent();
                agent.setId(agen.getInt("id"));
                agent.setAgentName(agen.getString("agen"));
                agent.setAgentCityId(agen.getInt("kota"));
                agent.setAgentCityName(agen.getString("namaKota"));
                agent.setAgentAddress(agen.getString("alamat"));
                agent.setAgentTelp(agen.getString("nomorTelepon"));
                agents.add(agent);
            }

            HashMap<String, Object> data = new HashMap<>();
            data.put("agents", agents);

            result.setResult(true);
            result.setData(data);
        }catch(JSONException e) {
            result.setResult(false);
            result.addError(e.getMessage());

            SWLog.e(AgentParser.class, e.getMessage());
        }
    }

    public SWResult getResult() {
        return result;
    }
}
