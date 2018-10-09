package org.tomislavgazica.weatherapp.networking;

import org.tomislavgazica.weatherapp.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    // okhttp klijnr, interceptor za logcat

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}