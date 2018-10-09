package org.tomislavgazica.weatherapp.ui.forecast.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tomislavgazica.weatherapp.R;
import org.tomislavgazica.weatherapp.model.OneDayForecast;
import org.tomislavgazica.weatherapp.ui.forecast.adapter.ItemListAdapter;
import org.tomislavgazica.weatherapp.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ForecastFragment extends Fragment {

    @BindView(R.id.forecast_item_list)
    RecyclerView forecastItemList;
    Unbinder unbinder;

    private ItemListAdapter itemListAdapter;

    private OneDayForecast oneDayForecast;

    public static ForecastFragment newInstance(OneDayForecast oneDayForecast) {
        Bundle data = new Bundle();
        data.putSerializable(Constants.FORECAST_DAY_KEY, oneDayForecast);
        ForecastFragment f = new ForecastFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            oneDayForecast = (OneDayForecast) getArguments().getSerializable(Constants.FORECAST_DAY_KEY);
        }

        itemListAdapter = new ItemListAdapter();

        forecastItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastItemList.setAdapter(itemListAdapter);

        itemListAdapter.setForecast(oneDayForecast);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
