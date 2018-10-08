package org.tomislavgazica.weatherapp.presentation;

import org.tomislavgazica.weatherapp.holder.ForecastHolder;
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.ui.forecast.fragment.ForecastFragmentContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForecastFragmentPresenter implements ForecastFragmentContract.Presenter {

    private ForecastHolder forecastHolder;
    private ForecastFragmentContract.View view;

    public ForecastFragmentPresenter() {
        forecastHolder = ForecastHolder.getInstance();
    }

    @Override
    public void setView(ForecastFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(Date dateToDisplay) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(dateToDisplay);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int allDays;

        Calendar allDates = Calendar.getInstance();
        List<Forecast> forecasts = new ArrayList<>();

        for (int i = 0; i < forecastHolder.getForecasts().size(); i++) {
            allDates.setTime(forecastHolder.getForecasts().get(i).getDate());
            allDays = allDates.get(Calendar.DAY_OF_MONTH);

            if (currentDay == allDays) {
                forecasts.add(forecastHolder.getForecasts().get(i));
            }
        }

        view.setData(forecasts);
    }
}
