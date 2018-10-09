package org.tomislavgazica.weatherapp.util;

import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.model.OneDayForecast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForcastSorterUtil {

    public static List<OneDayForecast> sortForecastData(List<Forecast> forecasts) {

        List<OneDayForecast> oneDayForecasts = new ArrayList<>();

        List<Date> dates = new ArrayList<>();

        for (int i = 0; i < forecasts.size(); i += 8) {
            dates.add(forecasts.get(i).getDate());
        }

        Calendar currentDate = Calendar.getInstance();
        Calendar allDates = Calendar.getInstance();
        int currentDay;
        int allDays;

        for (int i = 0; i < dates.size(); i++) {

            List<Forecast> sortedForecasts = new ArrayList<>();

            currentDate.setTime(dates.get(i));
            currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

            for (int j = 0; j < forecasts.size(); j++) {

                allDates.setTime(forecasts.get(j).getDate());
                allDays = allDates.get(Calendar.DAY_OF_MONTH);

                if (currentDay == allDays) {
                    sortedForecasts.add(forecasts.get(j));
                }
            }

            String formattedCurrentDate = formatDate(dates.get(i));
            oneDayForecasts.add(new OneDayForecast(formattedCurrentDate, sortedForecasts));

        }

        return oneDayForecasts;
    }

    private static String formatDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;

        return Integer.toString(day) + "." + Integer.toString(month) + ".";
    }

}
