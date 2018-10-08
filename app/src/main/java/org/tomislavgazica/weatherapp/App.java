package org.tomislavgazica.weatherapp;

import android.app.Application;

import org.tomislavgazica.weatherapp.holder.ForecastHolder;
import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.interactor.ApiInteractorImpl;
import org.tomislavgazica.weatherapp.networking.ApiService;
import org.tomislavgazica.weatherapp.networking.RetrofitUtil;

import retrofit2.Retrofit;

public class App extends Application {

    private static App sInstance;
    private static ApiInteractor apiInteractor;
    private static double latitude;
    private static double longitude;
    private static String currentCity = null;


    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        ForecastHolder forecastHolder = ForecastHolder.getInstance();

        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);

    }

    public static String getCurrentCity() {
        return currentCity;
    }

    public static void setCurrentCity(String currentCity) {
        App.currentCity = currentCity;
    }

    public static App getInstance() {
        return sInstance;
    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        App.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        App.longitude = longitude;
    }
}
