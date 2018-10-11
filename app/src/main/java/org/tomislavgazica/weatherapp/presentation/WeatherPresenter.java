package org.tomislavgazica.weatherapp.presentation;

import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.model.WeatherResponse;
import org.tomislavgazica.weatherapp.ui.weatherCurrent.WeatherContract;
import org.tomislavgazica.weatherapp.util.Constants;
import org.tomislavgazica.weatherapp.util.ConversionUtil;
import org.tomislavgazica.weatherapp.util.LocationDataUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.Presenter {

    private final ApiInteractor apiInteractor;

    private WeatherContract.View view;
    private LocationDataUtil locationDataUtil;

    private boolean isRefreshCalled = false;

    public WeatherPresenter(ApiInteractor apiInteractor) {
        this.apiInteractor = apiInteractor;
        locationDataUtil = LocationDataUtil.getInstance();
    }

   @Override
    public void setView(WeatherContract.View view) {
        this.view = view;
    }

    @Override
    public void refreshWeather() {
        isRefreshCalled = true;
        if (locationDataUtil.getCityName() != null) {
            getWeatherFromNet(locationDataUtil.getCityName());
        }else {
            getWeatherFromNet(Constants.DEFAULT_CITY);
        }
    }

    @Override
    public void getWeatherFromNet(String city) {
        apiInteractor.getWeatherFromName(getWeatherCallback(), city);
    }

    @Override
    public void getWeatherFromNet(double latitude, double longitude) {
        apiInteractor.getWeatherFromGps(getWeatherCallback(), latitude, longitude);
    }

    public Callback<WeatherResponse> getWeatherCallback() {
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
        if (!isRefreshCalled) {
            locationDataUtil.setCityName(weatherResponse.getName());
            isRefreshCalled = false;
        }
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
    public void getCityName(String cityName) {
        view.setCityName(cityName);
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
        view.setPressureValues(ConversionUtil.formatPressure(pressureValues));
    }

    @Override
    public void getHumidityValues(int humidity) {
        view.setHumidityValues(humidity);
    }

    @Override
    public void getWindValues(double windValues, double direction) {
        view.setWindValues(ConversionUtil.toKmhFromMph(windValues), direction);
    }

    @Override
    public void getWeatherIcon(String iconPath) {
        view.setWeatherIcon(iconPath);
    }

    @Override
    public void getDescriptionValues(String descriptionValues) {
        view.setDescriptionValues(descriptionValues);
    }

}
