package org.tomislavgazica.weatherapp.ui.forecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.tomislavgazica.weatherapp.App;
import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.model.ForecastResponse;
import org.tomislavgazica.weatherapp.presentation.ForecastPresenter;
import org.tomislavgazica.weatherapp.ui.forecast.adapter.DateListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastActivity extends AppCompatActivity implements ForecastContract.View {

    @BindView(R.id.forecast_items)
    RecyclerView forecastItems;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DateListAdapter dateListAdapter;
    private ForecastContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);

        initToolbar();

        forecastItems.setLayoutManager(new LinearLayoutManager(getApplication()));

        dateListAdapter = new DateListAdapter();
        dateListAdapter.setActivity(getParent());
        forecastItems.setAdapter(dateListAdapter);

        presenter = new ForecastPresenter(App.getApiInteractor());
        presenter.setView(this);
        presenter.getDataFromNet();
    }

    @Override
    public void setData(ForecastResponse forecastResponse) {
        dateListAdapter.setForecastResponse(forecastResponse);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getDataFromNet();
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
        if(id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
