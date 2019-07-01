package com.busefisensi.efisiensiagen.Parser;

import com.busefisensi.efisiensiagen.Model.Seat;
import com.busefisensi.efisiensiagen.R;
import com.javasoul.swframework.component.SWLog;
import com.javasoul.swframework.model.SWResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class SeatParser {
    private String html;
    private String tr;
    private String nomerSeat;
    private SWResult result = new SWResult();
    private SWResult resultStatus = new SWResult();

    public SeatParser(String json) {
        try {
            List<Seat> seats = new ArrayList<>();
//            List<Seat> seatStatus = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonSeat = jsonObject.getJSONObject("seating");
            JSONArray jsonstatus = jsonSeat.getJSONArray("status");
//            for(int i=0; i<jsonstatus.length(); i++){
//                Seat seat = new Seat();
//                JSONObject status = jsonstatus.getJSONObject(i);
//                seat.setNomorStatus(status.getString("nomor"));
//                seat.setTersedia(status.getString("tersedia"));
//                seatStatus.add(seat);
//
//                HashMap<String, Object> dataStatus = new HashMap<>();
//                dataStatus.put("seatStatus", seatStatus);
//
//                resultStatus.setResult(true);
//                resultStatus.setData(dataStatus);
//            }
            html = jsonSeat.getString("html");
            html = "<table border='0' cellpadding='0' cellspacing='0'><tr><td><seat id='supir' class='supir'>supir</seat></td><td><seat id='4' class='unavailable'>4</seat></td><td><seat id='8' class='available'>8</seat></td><td><seat id='12' class='available'>12</seat></td><td><seat id='16' class='available'>16</seat></td><td><seat id='20' class='available'>20</seat></td><td><seat id='24' class='available'>24</seat></td><td><seat id='28' class='unavailable'>28</seat></td><td><seat id='32' class='available'>32</seat></td><td><seat id='34' class='available'>34</seat></td><td><seat id='38' class='available'>38</seat></td><td><seat id='43' class='available'>43</seat></td></tr><tr><td></td><td><seat id='3' class='available'>3</seat></td><td><seat id='7' class='available'>7</seat></td><td><seat id='11' class='available'>11</seat></td><td><seat id='15' class='available'>15</seat></td><td><seat id='19' class='available'>19</seat></td><td><seat id='23' class='available'>23</seat></td><td><seat id='27' class='available'>27</seat></td><td><seat id='31' class='available'>31</seat></td><td><seat id='33' class='available'>33</seat></td><td><seat id='37' class='available'>37</seat></td><td><seat id='42' class='available'>42</seat></td></tr><tr><td><seat id='44' class='available'>44</seat></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><seat id='41' class='available'>41</seat></td></tr><tr><td></td><td><seat id='2' class='available'>2</seat></td><td><seat id='6' class='available'>6</seat></td><td><seat id='10' class='available'>10</seat></td><td><seat id='14' class='available'>14</seat></td><td><seat id='18' class='available'>18</seat></td><td><seat id='22' class='available'>22</seat></td><td><seat id='26' class='available'>26</seat></td><td><seat id='30' class='available'>30</seat></td><td></td><td><seat id='36' class='available'>36</seat></td><td><seat id='40' class='available'>40</seat></td></tr><tr><td></td><td><seat id='1' class='available'>1</seat></td><td><seat id='5' class='available'>5</seat></td><td><seat id='9' class='available'>9</seat></td><td><seat id='13' class='available'>13</seat></td><td><seat id='17' class='available'>17</seat></td><td><seat id='21' class='available'>21</seat></td><td><seat id='25' class='available'>25</seat></td><td><seat id='29' class='available'>29</seat></td><td></td><td><seat id='35' class='available'>35</seat></td><td><seat id='39' class='available'>39</seat></td></tr></table>";
            Document doc = Jsoup.parse(html);
            Elements table = doc.select("table");
            Elements barisPertama = table.select("tr");
            Elements td = barisPertama.select("td");

            Elements tr = table.select("tr");
            Elements seatTd = tr.select("td");

            for(int i=0; i<td.size();i++) {
                Seat seat = new Seat();
                seat.setNomor(seatTd.get(i).text());
                seat.setPhoto(R.drawable.seat);
                Elements seatStatus = seatTd.get(i).select("seat");
                seat.setTersedia(seatStatus.attr("class"));
//                Log.d("statuskursi", seatStatus.attr("class"));
                seats.add(seat);
                HashMap<String, Object> data = new HashMap<>();
                data.put("seats", seats);

                result.setResult(true);
                result.setData(data);
            }



        }catch (JSONException e){
            result.setResult(false);
            result.addError(e.getMessage());

            SWLog.e(SeatParser.class, e.getMessage());
        }
    }


    public SWResult getResult(){
        return result;
    }
}
