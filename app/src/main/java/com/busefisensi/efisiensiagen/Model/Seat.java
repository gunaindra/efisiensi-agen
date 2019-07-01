package com.busefisensi.efisiensiagen.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Seat implements Parcelable {

    private String nomor;
    private String nomorStatus;
    private String tersedia;
    private int photo;
    private int sizeBaris;

    public Seat(){}

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Seat createFromParcel(Parcel in){
            return new Seat(in);
        }

        public Seat[] newArray(int size){
            return new Seat[size];
        }
    };

    public Seat(Parcel in){
        this.nomorStatus = in.readString();
        this.tersedia = in.readString();
        this.nomor = in.readString();
        this.photo = in.readInt();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.nomor);
        dest.writeInt(this.photo);
        dest.writeString(this.nomorStatus);
        dest.writeString(this.tersedia);
    }


    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTersedia() {
        return tersedia;
    }

    public void setTersedia(String tersedia) {
        this.tersedia = tersedia;
    }

    public int getPhoto(){
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getSizeBaris(){
        return sizeBaris;
    }

    public void setSizeBaris(int sizeBaris){
        this.sizeBaris = sizeBaris;
    }

    public String getNomorStatus(){return nomorStatus;}

    public void setNomorStatus(String nomorStatus){
        this.nomorStatus = nomorStatus;
    }
}
