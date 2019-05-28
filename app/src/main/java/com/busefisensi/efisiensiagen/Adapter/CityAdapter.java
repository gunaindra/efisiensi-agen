package com.busefisensi.efisiensiagen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Model.Agent;
import com.busefisensi.efisiensiagen.Model.City;
import com.busefisensi.efisiensiagen.R;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private List<City> cities = new ArrayList<>();
    private AgentAdapter.OnClickListener onClickListener;

    public CityAdapter(Context context, List<Agent> agents, AgentAdapter.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;

        for(Agent agent: agents) {
            City city = new City();
            city.setId(agent.getAgentCityId());
            city.setCityName(agent.getAgentCityName());
            city.setAgents(getAgentsByCity(agent.getAgentCityId(), agents));

            if(!checkDuplicateCity(city)) {
                cities.add(city);
            }
        }
    }

    private Boolean checkDuplicateCity(City city) {
        for(City city2: cities) {
            if(city2.getId() == city.getId()) {
                return true;
            }
        }

        return false;
    }

    private List<Agent> getAgentsByCity(Integer cityId, List<Agent> agents) {
        List<Agent> agentInCity = new ArrayList<>();
        for(Agent agent: agents) {
            if(agent.getAgentCityId() == cityId) {
                agentInCity.add(agent);
            }
        }

        return agentInCity;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        City city = cities.get(i);

//        viewHolder.tvCityName.setText(city.getCityName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        AgentAdapter agentAdapter = new AgentAdapter(context, city.getAgents(), onClickListener);
        viewHolder.rvAgents.setLayoutManager(linearLayoutManager);
        viewHolder.rvAgents.setAdapter(agentAdapter);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_city,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCityName;
        private RecyclerView rvAgents;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            tvCityName = itemView.findViewById(R.id.tv_city_name);
            rvAgents = itemView.findViewById(R.id.rv_agents);
        }
    }
}
