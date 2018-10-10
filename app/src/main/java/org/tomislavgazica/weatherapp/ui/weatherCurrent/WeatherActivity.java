package org.tomislavgazica.weatherapp.ui.weatherCurrent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.presentation.WeatherPresenter;
import org.tomislavgazica.weatherapp.ui.forecast.ForecastActivity;
import org.tomislavgazica.weatherapp.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    @BindView(R.id.weather_city_name)
    TextView weatherCityName;

    @BindView(R.id.weather_weather_description)
    TextView weatherWeatherDescription;

    @BindView(R.id.weather_current_temp)
    TextView weatherCurrentTemp;

    @BindView(R.id.weather_humidity)
    TextView weatherHumidity;

    @BindView(R.id.weather_pressure)
    TextView weatherPressure;

    @BindView(R.id.weather_wind_direction)
    ImageView weatherWindDirection;

    @BindView(R.id.weather_wind)
    TextView weatherWind;

    @BindView(R.id.weather_min_temp)
    TextView weatherMinTemp;

    @BindView(R.id.weather_max_temp)
    TextView weatherMaxTemp;

    @BindView(R.id.weather_weather_image)
    ImageView weatherWeatherImage;

    @BindView(R.id.places_autocomplete_input_text_view)
    TextView placesAutocompleteInputTextView;

    private LocationManager locationManager;
    private WeatherContract.Presenter presenter;
    private LocationListener locationListener;

    private static final int LOCATION_UPDATE_TIME = 0;
    private static final int REQUEST_LOCATION_PERMISSION = 100;

    private int PLACE_PICKER_REQUEST = 1;
    private int PLACE_AUTOCOMPLETE_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        presenter = new WeatherPresenter(App.getApiInteractor());
        presenter.setView(this);

        initLocationManager();
        getLastKnownLocation();

    }
    
    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                presenter.getWeatherFromNet(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            }else {
                presenter.getWeatherFromNet(Constants.DEFAULT_CITY);
            }
        } else {
            presenter.getWeatherFromNet(Constants.DEFAULT_CITY);
        }
    }

    public void initLocationManager() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    presenter.getWeatherFromNet(location.getLatitude(), location.getLongitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_TIME, 0, locationListener);
            }

        }else {
            presenter.getWeatherFromNet(Constants.DEFAULT_CITY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {

            if (permissions.length == 1 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLastKnownLocation();
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_TIME, 0, locationListener);
                }
            }
        }
    }

    @OnClick({R.id.places_autocomplete_input_text_view, R.id.places_autocomplete_search_image, R.id.places_autocomplete_horizontal_bar})
    public void onPlacesAutocompleteClicked() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.open_maps_icon)
    public void onOpenPlacePickerImageClicked() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                presenter.getWeatherFromNet(place.getLatLng().latitude, place.getLatLng().longitude);
            }
        }

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                presenter.getWeatherFromNet(place.getLatLng().latitude, place.getLatLng().longitude);
            }
        }
    }

    @OnClick(R.id.weather_refresh_data)
    public void onRefreshImageClicked() {
        presenter.refreshWeather();
    }

    @OnClick(R.id.weather_five_day_data_button)
    public void onForecastButtonClicked() {
        Intent intent = new Intent(this, ForecastActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNetworkFailure() {
        Toast.makeText(getApplicationContext(), getString(R.string.network_error_text), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCityName(String cityName) {
        weatherCityName.setText(cityName);
    }

    @Override
    public void setCurrentTemperatureValues(String temperatureValues) {
        weatherCurrentTemp.setText(temperatureValues);
    }

    @Override
    public void setMinTemperatureValues(String minTemperatureValues) {
        weatherMinTemp.setText(minTemperatureValues);
    }

    @Override
    public void setMaxTemperatureValues(String maxTemperatureValues) {
        weatherMaxTemp.setText(maxTemperatureValues);
    }

    @Override
    public void setPressureValues(String pressureValues) {
        weatherPressure.setText(pressureValues);
    }

    @Override
    public void setHumidityValues(int humidity) {
        String string = Integer.toString(humidity) + " " + Constants.PERCENT;
        weatherHumidity.setText(string);
    }

    @Override
    public void setWindValues(String windValues, double direction) {
        weatherWind.setText(windValues);
        weatherWindDirection.setRotation((float) direction);
    }

    @Override
    public void setWeatherIcon(String iconPath) {
        Glide.with(this).load(Constants.IMAGE_BASE_URL + iconPath).into(weatherWeatherImage);
    }

    @Override
    public void setDescriptionValues(String descriptionValues) {
        weatherWeatherDescription.setText(descriptionValues.toUpperCase());
    }
}
