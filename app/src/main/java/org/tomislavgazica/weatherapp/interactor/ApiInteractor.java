package org.tomislavgazica.weatherapp.interactor;

import org.tomislavgazica.weatherapp.model.WeatherResponse;

import retrofit2.Callback;

public interface ApiInteractor {

    void getWeatherFromName(Callback<WeatherResponse> callback, String city);

    void getWeatherFromGps(Callback<WeatherResponse> callback, double latitude, double longitude);

}
