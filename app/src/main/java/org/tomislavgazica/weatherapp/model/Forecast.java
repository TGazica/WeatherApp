package org.tomislavgazica.weatherapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forecast {

    private Main main;
    private Weather[] weather = new Weather[1];
    private Wind wind;
    private String dt_txt;

    public Forecast() {
    }

    public Forecast(Main main, Weather[] weather, Wind wind, String dt_txt) {
        this.main = main;
        this.weather = weather;
        this.wind = wind;

    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather getWeatherObject() {
        return weather[0];
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public Date getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return sdf.parse(dt_txt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
