package org.tomislavgazica.weatherapp.model;

import java.io.Serializable;
import java.util.List;

public class OneDayForecast implements Serializable {

    private String date;
    private List<Forecast> forecasts;

    public OneDayForecast(String date, List<Forecast> forecasts) {
        this.date = date;
        this.forecasts = forecasts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts.clear();
        this.forecasts.addAll(forecasts);
    }
}
