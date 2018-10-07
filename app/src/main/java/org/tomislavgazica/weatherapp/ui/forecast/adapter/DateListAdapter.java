package org.tomislavgazica.weatherapp.ui.forecast.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.model.ForecastResponse;
import org.tomislavgazica.weatherapp.ui.forecast.viewHolder.DateListViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateListAdapter extends RecyclerView.Adapter<DateListViewHolder> {

    private ForecastResponse forecastResponse;
    private List<Date> dates = new ArrayList<>();
    private List<Forecast> forecastsOfTheDay = new ArrayList<>();
    private Activity activity;

    public void setForecastResponse(ForecastResponse forecastResponse) {
        this.forecastResponse = forecastResponse;
        this.dates.clear();

        for (int i = 0; i < forecastResponse.getList().size(); i += 8) {
            dates.add(forecastResponse.getList().get(i).getDate());
        }
        notifyDataSetChanged();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public DateListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_forcast_day, viewGroup, false);
        return new DateListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateListViewHolder dateListViewHolder, int i) {
        Date date = dates.get(i);
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

        Calendar iteratorDate = Calendar.getInstance();
        int iteratorDay;

        forecastsOfTheDay.clear();

        for (int j = 0; j < forecastResponse.getList().size(); j++) {

            iteratorDate.setTime(forecastResponse.getList().get(j).getDate());
            iteratorDay = iteratorDate.get(Calendar.DAY_OF_MONTH);

            if (currentDay == iteratorDay) {
                forecastsOfTheDay.add(forecastResponse.getList().get(j));
            }
        }

        dateListViewHolder.setActivity(activity);
        dateListViewHolder.setForecastsOfTheDay(forecastsOfTheDay);
        dateListViewHolder.setDate(date);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
}