package org.tomislavgazica.weatherapp.model;

import java.util.List;

public class ForecastResponse {

    private List<Forecast> list;

    public ForecastResponse() {
    }

    public ForecastResponse(List<Forecast> list) {
        this.list = list;
    }

    public List<Forecast> getList() {
        return list;
    }

}
