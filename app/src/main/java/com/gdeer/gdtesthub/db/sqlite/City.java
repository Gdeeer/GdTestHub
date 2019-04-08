package com.gdeer.gdtesthub.db.sqlite;

public class City {
    private int id;
    private String cityId;
    private String cityName;

    public City(String cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + id +
            ", cityId='" + cityId + '\'' +
            ", cityName='" + cityName + '\'' +
            '}';
    }
}
