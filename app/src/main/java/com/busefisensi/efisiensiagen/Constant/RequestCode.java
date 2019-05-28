package com.busefisensi.efisiensiagen.Constant;

public enum RequestCode {
    CHOOSE_DEPARTURE(10),
    CHOOSE_DESTINATION(11),
    CHOOSE_DATE(12),
    CHOOSE_SCHEDULE(13),
    CHOOSE_PASSENGER(14),
    ADD_OR_CHOOSE_PASSENGER(15),
    SEARCH_AGENT(15),
    CREATE_PASSENGER(16);

    int requestCode;

    RequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int get() {
        return requestCode;
    }
}
