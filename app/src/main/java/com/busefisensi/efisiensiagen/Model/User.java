package com.busefisensi.efisiensiagen.Model;

public class User {

    private String username, nama, nip, agen, token;

    public User(String nama, String nip, String agen, String token){
        this.nama = nama;
        this.nip = nip;
        this.agen = agen;
        this.token = token;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getAgen() {
        return agen;
    }

    public void setAgen(String agen) {
        this.agen = agen;
    }
}
