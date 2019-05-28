package com.busefisensi.efisiensiagen.Model;

import java.util.ArrayList;
import java.util.List;

public class City {

    private Integer id;
    private String cityName;
    private List<Agent> agents = new ArrayList<>();

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
