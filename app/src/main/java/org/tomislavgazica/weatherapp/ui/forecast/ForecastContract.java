package org.tomislavgazica.weatherapp.ui.forecast;

import java.util.Date;
import java.util.List;

public interface ForecastContract {

    interface View {

        void setForecastData(List<Date> dates);

        void onNetworkFail();

    }

    interface Presenter {

        void setView(ForecastContract.View view);

        void getForecastData();

    }

}
