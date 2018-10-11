package org.tomislavgazica.weatherapp.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConversionUtilTest {

    @Test
    public void toCelsiusFromKelvin() {
        double input = 273;
        String output;
        String expectedOutput = 0 + " " + Constants.TEMPERATURE_CELSIUS;

        output = ConversionUtil.toCelsiusFromKelvin(input);

        assertEquals(output, expectedOutput);

    }

    @Test
    public void toKmhFromMph() {
        double input = 1000;
        String output;
        String expectedOutput = 1609.3 + " " + Constants.SPEED_UNIT;

        output = ConversionUtil.toKmhFromMph(input);

        assertEquals(output, expectedOutput);
    }

    @Test
    public void formatPressure() {
        double input = 3056.847;
        String output;
        String expectedOutput = 3056.8 + Constants.PRESSURE_UNIT;

        output = ConversionUtil.formatPressure(input);

        assertEquals(output, expectedOutput);

    }
}