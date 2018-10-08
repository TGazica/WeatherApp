package org.tomislavgazica.weatherapp.presentation;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.holder.ForecastHolder;
import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.model.ForecastResponse;
import org.tomislavgazica.weatherapp.ui.forecast.ForecastContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastPresenter implements ForecastContract.Presenter {

    private ApiInteractor apiInteractor;
    private ForecastContract.View view;
    private ForecastHolder forecastHolder;

    public ForecastPresenter(ApiInteractor apiInteractor) {
        this.apiInteractor = apiInteractor;
        forecastHolder = ForecastHolder.getInstance();
    }

    @Override
    public void setView(ForecastContract.View view) {
        this.view = view;
    }

    @Override
    public void getForecastData() {
        apiInteractor.getForecast(forecastCallback(), App.getCurrentCity());
    }

    private Callback<ForecastResponse> forecastCallback() {
        return new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.body() != null) {
                    forecastHolder.setForecasts(response.body().getList());
                    getDates(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {

            }
        };
    }

    private void getDates(List<Forecast> forecasts) {
        List<Date> dates = new ArrayList<>();

        for (int i = 0; i < forecasts.size(); i += 8) {
            dates.add(forecasts.get(i).getDate());
        }
        view.setForecastData(dates);
    }
}
