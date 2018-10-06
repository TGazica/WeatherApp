package org.tomislavgazica.weatherapp.networking;

import org.tomislavgazica.weatherapp.model.WeatherResponse;
import org.tomislavgazica.weatherapp.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeatherFromName(@Query(Constants.APP_ID_KEY) String apiKey, @Query("q") String city);

    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeatherFromGps(@Query(Constants.APP_ID_KEY) String apiKey, @Query("lat") double latitude, @Query("lon") double longitude);
}
