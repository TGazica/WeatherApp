package org.tomislavgazica.weatherapp.ui.forecast.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.ui.forecast.viewHolder.ItemListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListViewHolder> {

    private List<Forecast> forecasts = new ArrayList<>();

    public ItemListAdapter(List<Forecast> forecasts) {
        this.forecasts.clear();
        this.forecasts.addAll(forecasts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_forecast, viewGroup, false);
        return new ItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder itemListViewHolder, int i) {
        Forecast forecast = forecasts.get(i);
        itemListViewHolder.setForcast(forecast);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }
}
