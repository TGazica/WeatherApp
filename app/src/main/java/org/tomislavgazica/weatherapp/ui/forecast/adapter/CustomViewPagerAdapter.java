package org.tomislavgazica.weatherapp.ui.forecast.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.tomislavgazica.weatherapp.ui.forecast.fragment.ForecastFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Date> dates = new ArrayList<>();

    public CustomViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setForecasts(List<Date> dates) {
        this.dates.clear();
        this.dates.addAll(dates);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return ForecastFragment.newInstance(dates.get(i));
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Date date = dates.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;

        return Integer.toString(day) + "." + Integer.toString(month) + ".";
    }
}
