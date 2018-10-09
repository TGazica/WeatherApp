package org.tomislavgazica.weatherapp.ui.forecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.model.OneDayForecast;
import org.tomislavgazica.weatherapp.presentation.ForecastPresenter;
import org.tomislavgazica.weatherapp.ui.forecast.adapter.CustomViewPagerAdapter;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastActivity extends AppCompatActivity implements ForecastContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.forecast_activity_view_pager)
    ViewPager forecastActivityViewPager;

    private CustomViewPagerAdapter adapter;
    private ForecastContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);
        initToolbar();
        initUI();

        presenter = new ForecastPresenter(App.getApiInteractor());
        presenter.setView(this);

        adapter = new CustomViewPagerAdapter(getSupportFragmentManager());

        forecastActivityViewPager.setAdapter(adapter);

        presenter.getForecastData();
    }

    private void initUI() {
        if (forecastActivityViewPager != null) {
            forecastActivityViewPager.setOffscreenPageLimit(2);
        }
    }

    private void initToolbar() {
        if (toolbar != null) {
            toolbar.setTitle(R.string.weather_forecast_title);
        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setForecastData(List<OneDayForecast> oneDayForecasts) {
        adapter.setForecasts(oneDayForecasts);
    }

    @Override
    public void onNetworkFail() {
        Toast.makeText(getApplicationContext(), getString(R.string.network_error_text), Toast.LENGTH_SHORT).show();
    }
}
