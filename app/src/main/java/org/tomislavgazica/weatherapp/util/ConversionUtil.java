package org.tomislavgazica.weatherapp.util;

import java.text.DecimalFormat;

public class ConversionUtil {

    public static String toCelsiusFromKelvin(double temperature) {
        DecimalFormat REAL_FORMATTER = new DecimalFormat("0.#");
        return REAL_FORMATTER.format(temperature - 273);
    }

    public static String toKmhFromMph(double speed) {
        DecimalFormat REAL_FORMATTER = new DecimalFormat("0.#");
        return REAL_FORMATTER.format(speed * 1.60934);
    }

}
