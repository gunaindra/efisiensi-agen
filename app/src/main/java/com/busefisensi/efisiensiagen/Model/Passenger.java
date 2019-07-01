package com.busefisensi.efisiensiagen.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Passenger implements Parcelable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;

    private Boolean selected = false;

    public Passenger() {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Passenger createFromParcel(Parcel in) {
            return new Passenger(in);
        }

        public Passenger[] newArray(int size) {
            return new Passenger[size];
        }
    };

    public Passenger(Parcel in) {
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.telephone = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.telephone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephon() {
        return telephone;
    }

    public void setTelephon(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
