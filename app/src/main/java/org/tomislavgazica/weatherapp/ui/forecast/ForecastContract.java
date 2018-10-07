package org.tomislavgazica.weatherapp.ui.forecast;

import org.tomislavgazica.weatherapp.model.ForecastResponse;

public interface ForecastContract {

    interface View{

        void setData(ForecastResponse forecastResponse);

    }

    interface Presenter{

        void getDataFromNet();

        void setView(ForecastContract.View view);

    }
}
