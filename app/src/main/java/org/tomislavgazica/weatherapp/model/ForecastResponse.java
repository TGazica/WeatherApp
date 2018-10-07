package org.tomislavgazica.weatherapp.model;

import java.util.List;

public class ForecastResponse {

    private City city;
    private List<Forecast> list;

    public ForecastResponse() {
    }

    public ForecastResponse(City city, List<Forecast> list) {
        this.city = city;
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }
}
