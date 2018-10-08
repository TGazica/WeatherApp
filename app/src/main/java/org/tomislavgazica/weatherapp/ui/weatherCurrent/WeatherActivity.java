package org.tomislavgazica.weatherapp.ui.weatherCurrent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.ui.forecast.ForecastActivity;
import org.tomislavgazica.weatherapp.ui.forecast.OnForecastClickListener;
import org.tomislavgazica.weatherapp.ui.weatherCurrent.fragment.WeatherDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements OnForecastClickListener {

    private SupportPlaceAutocompleteFragment placeAutocompleteFragment;
    private WeatherDetailsFragment weatherDetailsFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private int PLACE_PICKER_REQUEST = 1;

    @BindView(R.id.main_weather_details)
    FrameLayout mainWeatherDetails;
    @BindView(R.id.open_maps_icon)
    ImageView openMapsIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        initWeatherDetails();
        initPlaces();

    }

    private void initPlaces() {
        placeAutocompleteFragment = (SupportPlaceAutocompleteFragment) getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();

        placeAutocompleteFragment.setFilter(filter);

        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                App.setCurrentCity(place.getName().toString());
                weatherDetailsFragment.refreshData();
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplicationContext(), getString(R.string.gps_error), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initWeatherDetails() {
        weatherDetailsFragment = new WeatherDetailsFragment();
        weatherDetailsFragment.setListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_weather_details, weatherDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void getForecast() {
        Intent intent = new Intent(this, ForecastActivity.class);
        startActivity(intent);
    }

//    @OnClick(R.id.open_maps_icon)
//    public void openMap() {
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(this, data);
//                weatherDetailsFragment.refreshData(place.getLatLng().latitude, place.getLatLng().longitude);
//            }
//        }
//    }
}
