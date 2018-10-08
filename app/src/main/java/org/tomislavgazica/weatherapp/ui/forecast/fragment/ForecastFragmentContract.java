package org.tomislavgazica.weatherapp.ui.forecast.fragment;

import org.tomislavgazica.weatherapp.model.Forecast;

import java.util.Date;
import java.util.List;

public interface ForecastFragmentContract {

    interface View {

        void setData(List<Forecast> forecasts);

    }

    interface Presenter {

        void getData(Date dateToDisplay);

        void setView(ForecastFragmentContract.View view);

    }
}
