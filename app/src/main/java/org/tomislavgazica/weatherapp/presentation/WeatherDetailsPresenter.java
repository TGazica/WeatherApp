package org.tomislavgazica.weatherapp.presentation;

import android.app.Activity;
import android.content.Context;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.model.WeatherResponse;
import org.tomislavgazica.weatherapp.ui.weatherCurrent.fragment.WeatherDetailsContract;
import org.tomislavgazica.weatherapp.util.Constants;
import org.tomislavgazica.weatherapp.util.ConversionUtil;
import org.tomislavgazica.weatherapp.util.GpsListener;
import org.tomislavgazica.weatherapp.util.GpsUtil;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailsPresenter implements WeatherDetailsContract.Presenter, GpsListener {

    private final ApiInteractor apiInteractor;

    private WeatherDetailsContract.View view;

    public WeatherDetailsPresenter(ApiInteractor apiInteractor) {
        this.apiInteractor = apiInteractor;
    }

    public void setGpsUtil(Context context, Activity activity) {
        GpsUtil gpsUtil = new GpsUtil(context, activity, this);
    }

    @Override
    public void setView(WeatherDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void getCityName(String cityName) {
        view.setCityName(cityName);
    }

    @Override
    public void refreshWeather() {
        getWeatherFromNet(App.getCurrentCity());
    }

    @Override
    public void refreshWeather(double langitude, double longitude) {
        apiInteractor.getWeatherFromGps(getWeatherCallback(), langitude, longitude);
    }

    @Override
    public void getWeatherFromNet(String city) {
        apiInteractor.getWeatherFromName(getWeatherCallback(), city);
    }

    @Override
    public void getWeatherFromNet(double latitude, double longitude) {
        apiInteractor.getWeatherFromGps(getWeatherCallback(), latitude, longitude);
    }

    private Callback<WeatherResponse> getWeatherCallback() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.body() != null) {
                    getWeather(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                view.onNetworkFailure();
            }
        };
    }

    @Override
    public void getWeather(WeatherResponse weatherResponse) {
        getCurrentTemperatureValues(weatherResponse.getMain().getTemp_max());
        getPressureValues(weatherResponse.getMain().getPressure());
        getDescriptionValues(weatherResponse.getWeatherObject().getDescription());
        createWeatherIconValue(weatherResponse.getWeatherObject().getMain());
        getMinTemperatureValues(weatherResponse.getMain().getTemp_min());
        getMaxTemperatureValues(weatherResponse.getMain().getTemp_max());
        getHumidityValues(weatherResponse.getMain().getHumidity());
        getCityName(weatherResponse.getName());
        App.setCurrentCity(weatherResponse.getName());
        getWindValues(weatherResponse.getWind().getSpeed(), weatherResponse.getWind().getDeg());
    }

    @Override
    public void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    getWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    getWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    getWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    getWeatherIcon(Constants.MIST);
                    break;
                }
                case Constants.FOG_CASE: {
                    getWeatherIcon(Constants.MIST);
                    break;
                }
                case Constants.HAZE_CASE: {
                    getWeatherIcon(Constants.MIST);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    getWeatherIcon(Constants.CLOUD);
                    break;
                }

                case Constants.THUNDERSTORM_CASE: {
                    getWeatherIcon(Constants.CLOUD);
                    break;
                }
            }
    }

    @Override
    public void getCurrentTemperatureValues(double temperatureValues) {
        view.setCurrentTemperatureValues(ConversionUtil.toCelsiusFromKelvin(temperatureValues));
    }

    @Override
    public void getMinTemperatureValues(double minTemperatureValues) {
        view.setMinTemperatureValues(ConversionUtil.toCelsiusFromKelvin(minTemperatureValues));
    }

    @Override
    public void getMaxTemperatureValues(double maxTemperatureValues) {
        view.setMaxTemperatureValues(ConversionUtil.toCelsiusFromKelvin(maxTemperatureValues));
    }

    @Override
    public void getPressureValues(double pressureValues) {
        DecimalFormat REAL_FORMATTER = new DecimalFormat("0.#");
        view.setPressureValues(REAL_FORMATTER.format(pressureValues));
    }

    @Override
    public void getHumidityValues(int humidity) {
        view.setHumidityValues(humidity);
    }

    @Override
    public void getWindValues(double windValues, double direction) {
        view.setWindValues(ConversionUtil.toKmhFromMph(windValues), -direction);
    }

    @Override
    public void getWeatherIcon(String iconPath) {
        view.setWeatherIcon(iconPath);
    }

    @Override
    public void getDescriptionValues(String descriptionValues) {
        view.setDescriptionValues(descriptionValues);
    }

    @Override
    public void onLocationSuccess() {
        if (App.getCurrentCity() != null) {
            getWeatherFromNet(App.getCurrentCity());
        } else {
            getWeatherFromNet(App.getLatitude(), App.getLongitude());
        }
    }

    @Override
    public void onLocationFail() {
        getWeatherFromNet(Constants.DEFAULT_CITY);
    }
}
