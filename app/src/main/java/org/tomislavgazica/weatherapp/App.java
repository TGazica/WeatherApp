package org.tomislavgazica.weatherapp;

import android.app.Application;

import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.interactor.ApiInteractorImpl;
import org.tomislavgazica.weatherapp.networking.ApiService;
import org.tomislavgazica.weatherapp.networking.RetrofitUtil;
import org.tomislavgazica.weatherapp.util.LocationDataUtil;

import retrofit2.Retrofit;

public class App extends Application {

    private static App sInstance;
    private static ApiInteractor apiInteractor;
    private LocationDataUtil locationDataUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        locationDataUtil = LocationDataUtil.getInstance();

        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);

    }

    public static App getInstance() {
        return sInstance;
    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }
}
