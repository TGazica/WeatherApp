package org.tomislavgazica.weatherapp.ui.forecast.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.util.Constants;
import org.tomislavgazica.weatherapp.util.ConversionUtil;

import java.text.DecimalFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.item_weather_description)
    TextView itemWeatherDescription;
    @BindView(R.id.item_time)
    TextView itemTime;
    @BindView(R.id.item_temp)
    TextView itemTemp;
    @BindView(R.id.item_wind_direction)
    ImageView itemWindDirection;
    @BindView(R.id.item_wind)
    TextView itemWind;
    @BindView(R.id.item_pressure)
    TextView itemPressure;
    @BindView(R.id.item_humidity)
    TextView itemHumidity;

    private static final String HOUR_UNIT = " h";

    public ItemListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void setForecast(Forecast forecast) {
        String description = forecast.getWeatherObject().getDescription().toUpperCase();
        Calendar time = Calendar.getInstance();
        time.setTime(forecast.getDate());
        String timeOfDay = Integer.toString(time.get(Calendar.HOUR_OF_DAY));
        if (timeOfDay.length() == 1) {
            timeOfDay = "0" + timeOfDay;
        }
        timeOfDay = timeOfDay + HOUR_UNIT;
        String temp = ConversionUtil.toCelsiusFromKelvin(forecast.getMain().getTemp()) + Constants.TEMPERATURE_CELSIUS;
        String speed = ConversionUtil.toKmhFromMph(forecast.getWind().getSpeed()) + Constants.SPEED_UNIT;
        DecimalFormat REAL_FORMATTER = new DecimalFormat("0.#");
        String pressure = REAL_FORMATTER.format(forecast.getMain().getPressure()) + Constants.PRESSURE_UNIT;
        String humidity = Integer.toString(forecast.getMain().getHumidity()) + Constants.PERCENT;

        itemWeatherDescription.setText(description);
        itemTemp.setText(temp);
        itemTime.setText(timeOfDay);
        itemWindDirection.setRotation((float) -forecast.getWind().getDeg());
        itemPressure.setText(pressure);
        itemHumidity.setText(humidity);
        itemWind.setText(speed);
    }

}
