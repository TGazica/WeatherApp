package org.tomislavgazica.weatherapp.ui.weatherCurrent.fragment;

import android.app.Activity;
import android.content.Context;

import org.tomislavgazica.weatherapp.model.WeatherResponse;

public interface WeatherDetailsContract {

    interface View {

        void onNetworkFailure();

        void setCityName(String cityName);

        void setCurrentTemperatureValues(String temperatureValues);

        void setMinTemperatureValues(String minTemperatureValues);

        void setMaxTemperatureValues(String maxTemperatureValues);

        void setPressureValues(String pressureValues);

        void setHumidityValues(int humidity);

        void setWindValues(String windValues, double direction);

        void setWeatherIcon(String iconPath);

        void setDescriptionValues(String descriptionValues);

    }

    interface Presenter {

        void setGpsUtil(Context context, Activity activity);

        void refreshWeather();

        void refreshWeather(double langitude, double longitude);

        void getWeatherFromNet(String city);

        void getWeatherFromNet(double latitude, double longitude);

        void setView(View view);

        void getCityName(String cityName);

        void getWeather(WeatherResponse weatherResponse);

        void createWeatherIconValue(String description);

        void getCurrentTemperatureValues(double temperatureValues);

        void getMinTemperatureValues(double minTemperatureValues);

        void getMaxTemperatureValues(double maxTemperatureValues);

        void getPressureValues(double pressureValues);

        void getHumidityValues(int humidity);

        void getWindValues(double windValues, double direction);

        void getWeatherIcon(String iconPath);

        void getDescriptionValues(String descriptionValues);

    }

}
