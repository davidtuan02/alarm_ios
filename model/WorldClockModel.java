package com.example.myalarm.model;

public class WorldClockModel {
    public WorldClockModel() {
    }

    public WorldClockModel(String id, String city, String country, String timezone) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.timezone = timezone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String id;

    @Override
    public String toString() {
        return "WorldClockModel{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }

    public String city;
    public String country;
    public String timezone;
}
