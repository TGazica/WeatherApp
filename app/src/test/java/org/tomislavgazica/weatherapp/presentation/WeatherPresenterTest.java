package org.tomislavgazica.weatherapp.presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.tomislavgazica.weatherapp.interactor.ApiInteractor;
import org.tomislavgazica.weatherapp.ui.weatherCurrent.WeatherContract;

import static org.junit.Assert.*;

public class WeatherPresenterTest {

    private WeatherPresenter presenter;

    @Mock
    private WeatherContract.View view;
    @Mock
    private ApiInteractor apiInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = Mockito.spy(new WeatherPresenter(apiInteractor));
        presenter.setView(view);
    }

    @Test
    public void getCityName() {
        String input = "Osijek";
        String output = "Osijek";

        presenter.getCityName(input);

        Mockito.verify(view).setCityName(output);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);
    }

    @Test
    public void getCurrentTemperatureValues() {
        double input = 273;
        String output = "0 °C";

        presenter.getCurrentTemperatureValues(input);

        Mockito.verify(view).setCurrentTemperatureValues(output);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);

    }

    @Test
    public void getMinTemperatureValues() {
        double input = 273;
        String output = "0 °C";

        presenter.getCurrentTemperatureValues(input);

        Mockito.verify(view).setMinTemperatureValues(output);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);
    }

    @Test
    public void getMaxTemperatureValues() {
        double input = 273;
        String output = "0 °C";

        presenter.getCurrentTemperatureValues(input);

        Mockito.verify(view).setMaxTemperatureValues(output);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);
    }

    @Test
    public void getPressureValues() {
        double input = 1000.843;
        String output = "1000.8 bar";

        presenter.getPressureValues(input);

        Mockito.verify(view).setPressureValues(output);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);
    }

    @Test
    public void getHumidityValues() {
        int input = 24;
        int output = 24;

        presenter.getHumidityValues(input);

        Mockito.verify(view).setHumidityValues(output);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);
    }

    @Test
    public void getWindValues() {
        double inputWindSpeed = 5.46;
        double inputWindDegrees = 168.9;
        String outputWindSpeed = "8.8 km/h";
        double outputWindDegrees = 168.9;


        presenter.getWindValues(inputWindSpeed, inputWindDegrees);

        Mockito.verify(view).setWindValues(outputWindSpeed, outputWindDegrees);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);
    }

    @Test
    public void getDescriptionValues() {
        String input = "heavy rain";
        String output = "heavy rain";

        presenter.getDescriptionValues(input);

        Mockito.verify(view).setDescriptionValues(output);
        Mockito.verifyNoMoreInteractions(view, apiInteractor);
    }
}