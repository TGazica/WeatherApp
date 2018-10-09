package org.tomislavgazica.weatherapp.presentation;

import org.tomislavgazica.weatherapp.holder.ForecastHolder;
import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.model.ForecastResponse;
import org.tomislavgazica.weatherapp.ui.forecast.ForecastContract;
import org.tomislavgazica.weatherapp.util.ForcastSorterUtil;
import org.tomislavgazica.weatherapp.util.LocationDataUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastPresenter implements ForecastContract.Presenter {

    private ApiInteractor apiInteractor;
    private ForecastContract.View view;
    private ForecastHolder forecastHolder;
    private LocationDataUtil locationDataUtil;

    public ForecastPresenter(ApiInteractor apiInteractor) {
        this.apiInteractor = apiInteractor;
        forecastHolder = ForecastHolder.getInstance();
        locationDataUtil = LocationDataUtil.getInstance();
    }

    @Override
    public void setView(ForecastContract.View view) {
        this.view = view;
    }

    @Override
    public void getForecastData() {
        apiInteractor.getForecast(forecastCallback(), locationDataUtil.getCityName());
    }

    private Callback<ForecastResponse> forecastCallback() {
        return new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.body() != null) {
                    forecastHolder.setForecasts(response.body().getList());
                    sortData(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                    view.onNetworkFail();
            }
        };
    }

    private void sortData(List<Forecast> forecasts) {
        view.setForecastData(ForcastSorterUtil.sortForecastData(forecasts));
    }
}
