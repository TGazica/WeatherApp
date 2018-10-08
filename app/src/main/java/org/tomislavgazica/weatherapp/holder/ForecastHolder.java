package org.tomislavgazica.weatherapp.holder;

import org.tomislavgazica.weatherapp.model.Forecast;

import java.util.ArrayList;
import java.util.List;

public class ForecastHolder {

    private static ForecastHolder forecastHolder;
    private List<Forecast> forecasts;

    private ForecastHolder() {
        forecasts = new ArrayList<>();
    }

    public static ForecastHolder getInstance() {
        if (forecastHolder == null) {
            forecastHolder = new ForecastHolder();
        }
        return forecastHolder;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
