package org.tomislavgazica.weatherapp.interactor;

import org.tomislavgazica.weatherapp.model.WeatherResponse;
import org.tomislavgazica.weatherapp.networking.ApiService;
import org.tomislavgazica.weatherapp.util.Constants;

import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor {

    private final ApiService apiService;

    public ApiInteractorImpl(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public void getWeatherFromName(Callback<WeatherResponse> callback, String city) {
        apiService.getWeatherFromName(Constants.API_KEY_OPENWEATHERMAPS, city).enqueue(callback);
    }

    @Override
    public void getWeatherFromGps(Callback<WeatherResponse> callback, double latitude, double longitude) {
        apiService.getWeatherFromGps(Constants.API_KEY_OPENWEATHERMAPS, latitude, longitude).enqueue(callback);
    }
}
