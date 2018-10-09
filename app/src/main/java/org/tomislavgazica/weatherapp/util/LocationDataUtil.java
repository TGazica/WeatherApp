package org.tomislavgazica.weatherapp.util;

public class LocationDataUtil {

    private static LocationDataUtil locationDataUtil;
    private String cityName;
    private double latitude;
    private double longitude;

    private LocationDataUtil(){

    }

    public static LocationDataUtil getInstance(){
        if (locationDataUtil == null){
            locationDataUtil = new LocationDataUtil();
        }
        return locationDataUtil;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
