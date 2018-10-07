package org.tomislavgazica.weatherapp.presentation;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.model.ForecastResponse;
import org.tomislavgazica.weatherapp.ui.forecast.ForecastContract;
import org.tomislavgazica.weatherapp.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastPresenter implements ForecastContract.Presenter {

    private ApiInteractor apiInteractor;
    private ForecastContract.View view;

    public ForecastPresenter(ApiInteractor apiInteractor){
        this.apiInteractor = apiInteractor;
    }

    @Override
    public void getDataFromNet() {
        apiInteractor.getForecast(callbackForecast(), App.getCurrentCity());
    }

    private Callback<ForecastResponse> callbackForecast() {
        return new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.body() != null){
                    view.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {

            }
        };
    }

    @Override
    public void setView(ForecastContract.View view) {
        this.view = view;
    }
}
