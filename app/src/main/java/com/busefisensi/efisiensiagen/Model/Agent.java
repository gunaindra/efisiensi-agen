package com.busefisensi.efisiensiagen.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Agent implements Parcelable {

    private Integer id;
    private String agentName;
    private Integer agentCityId;
    private String agentCityName;
    private String agentAddress;
    private String agentTelp;

    public Agent() {

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Agent createFromParcel(Parcel in) {
            return new Agent(in);
        }

        public Agent[] newArray(int size) {
            return new Agent[size];
        }
    };

    public Agent(Parcel in) {
        this.id = in.readInt();
        this.agentName = in.readString();
        this.agentCityId = in.readInt();
        this.agentCityName = in.readString();
        this.agentAddress = in.readString();
        this.agentTelp = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.agentName);
        dest.writeInt(this.agentCityId);
        dest.writeString(this.agentCityName);
        dest.writeString(this.agentAddress);
        dest.writeString(this.agentTelp);
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentCityId() {
        return agentCityId;
    }

    public void setAgentCityId(Integer agentCityId) {
        this.agentCityId = agentCityId;
    }

    public String getAgentCityName() {
        return agentCityName;
    }

    public void setAgentCityName(String agentCityName) {
        this.agentCityName = agentCityName;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public String getAgentTelp() {
        return agentTelp;
    }

    public void setAgentTelp(String agentTelp) {
        this.agentTelp = agentTelp;
    }
}
