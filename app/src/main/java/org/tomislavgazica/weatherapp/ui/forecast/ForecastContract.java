package org.tomislavgazica.weatherapp.ui.forecast;

import org.tomislavgazica.weatherapp.model.OneDayForecast;

import java.util.Date;
import java.util.List;

public interface ForecastContract {

    interface View {

        void setForecastData(List<OneDayForecast> oneDayForecasts);

        void onNetworkFail();

    }

    interface Presenter {

        void setView(ForecastContract.View view);

        void getForecastData();

    }

}
