package com.busefisensi.efisiensiagen.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Schedule implements Parcelable {

    private Integer id;
    private String schedule;
    private String departureTime;
    private String arriveTime;
    private String upTime;
    private String downTime;
    private Long price;
    private Integer emptyChairs;
    private String busType;

    public Schedule(){
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Schedule createFromParcel(Parcel in){
            return new Schedule(in);
        }
        public Schedule[] newArray(int size){
            return new Schedule[size];
        }
    };

    public Schedule(Parcel in){
        this.id = in.readInt();
        this.schedule = in.readString();
        this.departureTime = in.readString();
        this.arriveTime = in.readString();
        this.upTime = in.readString();
        this.downTime = in.readString();
        this.price = in.readLong();
        this.emptyChairs = in.readInt();
        this.busType = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.id);
        dest.writeString(this.schedule);
        dest.writeString(this.departureTime);
        dest.writeString(this.arriveTime);
        dest.writeString(this.upTime);
        dest.writeString(this.downTime);
        dest.writeLong(this.price);
        dest.writeInt(this.emptyChairs);
        dest.writeString(this.busType);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getEmptyChairs() {
        return emptyChairs;
    }

    public void setEmptyChairs(Integer emptyChairs) {
        this.emptyChairs = emptyChairs;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

}
