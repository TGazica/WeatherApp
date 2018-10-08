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
import org.tomislavgazica.weatherapp.model.Forecast;
import org.tomislavgazica.weatherapp.presentation.ForecastFragmentPresenter;
import org.tomislavgazica.weatherapp.ui.forecast.adapter.ItemListAdapter;
import org.tomislavgazica.weatherapp.util.Constants;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ForecastFragment extends Fragment implements ForecastFragmentContract.View {

    @BindView(R.id.forecast_item_list)
    RecyclerView forecastItemList;
    Unbinder unbinder;

    private ForecastFragmentContract.Presenter presenter;
    private ItemListAdapter itemListAdapter;

    private Date dateToDisplay;

    public static ForecastFragment newInstance(Date date) {
        Bundle data = new Bundle();
        data.putSerializable(Constants.FORECAST_DAY_KEY, date);
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
            dateToDisplay = (Date) getArguments().getSerializable(Constants.FORECAST_DAY_KEY);
        }

        itemListAdapter = new ItemListAdapter();

        forecastItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastItemList.setAdapter(itemListAdapter);

        presenter = new ForecastFragmentPresenter();
        presenter.setView(this);
        presenter.getData(dateToDisplay);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setData(List<Forecast> forecasts) {
        itemListAdapter.setForecasts(forecasts);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(dateToDisplay);
    }
}
