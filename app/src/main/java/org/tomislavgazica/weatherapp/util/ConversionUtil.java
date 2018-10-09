package org.tomislavgazica.weatherapp.util;

import java.text.DecimalFormat;

public class ConversionUtil {

    public static String toCelsiusFromKelvin(double temperature) {
        DecimalFormat formatter = new DecimalFormat("0.#");
        return formatter.format(temperature - 273) + " " + Constants.TEMPERATURE_CELSIUS;
    }

    public static String toKmhFromMph(double speed) {
        DecimalFormat formatter = new DecimalFormat("0.#");
        return formatter.format(speed * 1.60934) + " " + Constants.SPEED_UNIT;
    }

    public static String formatPressure(double pressure){
        DecimalFormat formatter = new DecimalFormat("0.#");
        return formatter.format(pressure) + Constants.PRESSURE_UNIT;
    }


}
