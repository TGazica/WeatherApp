package org.tomislavgazica.weatherapp.ui.forecast.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.tomislavgazica.weatherapp.model.OneDayForecast;
import org.tomislavgazica.weatherapp.ui.forecast.fragment.ForecastFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomViewPagerAdapter extends FragmentPagerAdapter {

    private final List<OneDayForecast> oneDayForecasts = new ArrayList<>();

    public CustomViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setForecasts(List<OneDayForecast> oneDayForecasts) {
        this.oneDayForecasts.clear();
        this.oneDayForecasts.addAll(oneDayForecasts);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return ForecastFragment.newInstance(oneDayForecasts.get(i));
    }

    @Override
    public int getCount() {
        return oneDayForecasts.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return oneDayForecasts.get(position).getDate();
    }
}
