package org.tomislavgazica.weatherapp.ui.weatherCurrent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.presentation.WeatherDetailsPresenter;
import org.tomislavgazica.weatherapp.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WeatherDetailsFragment extends Fragment implements WeatherDetailsContract.View {

    @BindView(R.id.weather_fragment_city_name)
    TextView weatherFragmentCityName;

    @BindView(R.id.weather_fragment_weather_description)
    TextView weatherFragmentWeatherDescription;

    @BindView(R.id.weather_fragment_weather_image)
    ImageView weatherFragmentWeatherImage;

    @BindView(R.id.weather_fragment_min_temp)
    TextView weatherFragmentMinTemp;

    @BindView(R.id.weather_fragment_max_temp)
    TextView weatherFragmentMaxTemp;

    @BindView(R.id.weather_fragment_current_temp)
    TextView weatherFragmentCurrentTemp;

    @BindView(R.id.weather_fragment_humidity)
    TextView weatherFragmentHumidity;

    @BindView(R.id.weather_fragment_pressure)
    TextView weatherFragmentPressure;

    @BindView(R.id.weather_fragment_wind_direction)
    ImageView weatherFragmentWindDirection;

    @BindView(R.id.weather_fragment_wind)
    TextView weatherFragmentWind;

    private Unbinder unbinder;

    private WeatherDetailsContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new WeatherDetailsPresenter(App.getApiInteractor());
        presenter.setView(this);
        presenter.setGpsUtil(getContext(), getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.weather_fragment_refresh_data)
    public void refreshData(){
        presenter.refreshWeather();
    }

    @Override
    public void onNetworkFailure() {
        Toast.makeText(getContext(), getString(R.string.network_error_text), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCityName(String cityName) {
        weatherFragmentCityName.setText(cityName);
    }

    @Override
    public void setCurrentTemperatureValues(String temperatureValues) {
        String string = temperatureValues + " " + Constants.TEMPERATURE_CELSIUS;
        weatherFragmentCurrentTemp.setText(string);
    }

    @Override
    public void setMinTemperatureValues(String minTemperatureValues) {
        String string = minTemperatureValues + " " + Constants.TEMPERATURE_CELSIUS;
        weatherFragmentMinTemp.setText(string);
    }

    @Override
    public void setMaxTemperatureValues(String maxTemperatureValues) {
        String string = maxTemperatureValues + " " + Constants.TEMPERATURE_CELSIUS;
        weatherFragmentMaxTemp.setText(string);
    }

    @Override
    public void setPressureValues(String pressureValues) {
        String string = pressureValues + " " + Constants.PRESSURE_UNIT;
        weatherFragmentPressure.setText(string);
    }

    @Override
    public void setHumidityValues(int humidity) {
        String string = Integer.toString(humidity) + " " + Constants.PERCENT;
        weatherFragmentHumidity.setText(string);
    }

    @Override
    public void setWindValues(String windValues, double direction) {
        String string = windValues + " " + Constants.SPEED_UNIT;
        weatherFragmentWind.setText(string);
        weatherFragmentWindDirection.setRotation((float) direction);
    }

    @Override
    public void setWeatherIcon(String iconPath) {
        Glide.with(this).load(Constants.IMAGE_BASE_URL + iconPath).into(weatherFragmentWeatherImage);
    }

    @Override
    public void setDescriptionValues(String descriptionValues) {
        weatherFragmentWeatherDescription.setText(descriptionValues.toUpperCase());
    }
}
