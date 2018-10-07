package org.tomislavgazica.weatherapp.ui.forecast.viewHolder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.ui.forecast.adapter.ItemListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_forecast_date)
    TextView itemForecastDate;
    @BindView(R.id.item_list)
    RecyclerView itemList;

    private Activity activity;

    private List<Forecast> forecastsOfTheDay = new ArrayList<>();

    public DateListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setForecastsOfTheDay(List<Forecast> forecastsOfTheDay) {
        this.forecastsOfTheDay.clear();
        this.forecastsOfTheDay.addAll(forecastsOfTheDay);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;

        String dateToShow = Integer.toString(day) + "." + Integer.toString(month) + ".";
        itemForecastDate.setText(dateToShow);

        ItemListAdapter itemListAdapter = new ItemListAdapter(forecastsOfTheDay);

        itemList.setLayoutManager(new LinearLayoutManager(activity));
        itemList.setAdapter(itemListAdapter);
    }

}
