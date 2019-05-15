package com.busefisensi.efisiensiagen.Model;

public class User {

    private String username, nama, nik, agen;

    public User(String nama, String nik, String agen){
        this.nama = nama;
        this.nik = nik;
        this.agen = agen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAgen() {
        return agen;
    }

    public void setAgen(String agen) {
        this.agen = agen;
    }
}
