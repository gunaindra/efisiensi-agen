package com.busefisensi.efisiensiagen.Model;

public class Menu {

    private Integer menuIcon;
    private String namaIcon;

    public Menu(Integer menuIcon, String namaIcon){
        this.menuIcon = menuIcon;
        this.namaIcon = namaIcon;
    }

    public Integer getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(Integer menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getNamaIcon() {
        return namaIcon;
    }

    public void setNamaIcon(String namaIcon) {
        this.namaIcon = namaIcon;
    }
}
