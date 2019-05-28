package com.busefisensi.efisiensiagen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.busefisensi.efisiensiagen.Model.Menu;
import com.busefisensi.efisiensiagen.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<Menu> menus;
    private Context context;


    public MenuAdapter(Context context, List<Menu> menus){
        this.menus = menus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_view_list, null);
        ViewHolder vh = new ViewHolder(layoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.ivMenu.setImageResource(menus.get(position).getMenuIcon());
        holder.tvMenu.setText(menus.get(position).getNamaIcon());
    }

    @Override
    public int getItemCount(){
        return this.menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivMenu;
        private TextView tvMenu;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            ivMenu = itemView.findViewById(R.id.ivMenu);
            tvMenu = itemView.findViewById(R.id.tvMenu);

        }
    }
}
