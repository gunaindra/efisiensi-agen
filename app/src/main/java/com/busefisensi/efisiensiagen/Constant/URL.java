package com.busefisensi.efisiensiagen.Constant;

public enum URL {

    LOGINURL("http://rbt.arutala.co.id/efisiensi-agen/v1/login"),
    DEPOSIT("http://rbt.arutala.co.id/efisiensi-agen/v1/deposit"),
    AGEN("http://rbt.arutala.co.id/efisiensi-agen/v1/agen"),
    AGENT_ORIGIN("http://rbt.arutala.co.id/efisiensi-agen/v1/agen/naik"),
    AGENT_DESTINATION("http://rbt.arutala.co.id/efisiensi-agen/v1/agen/turun/");

    String url;
    URL(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }
}
