package se.iths.weatherforecast.entity;

import java.time.LocalDateTime;

public class Weather {
    LocalDateTime timeStamp;
    double temperature;
    double humidity;
    String origin;

    public Weather() {
    }

    public Weather(LocalDateTime timeStamp, double temperature, double humidity, String origin) {
        this.timeStamp = timeStamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Tid: " + timeStamp + "\n" +
                "Temperatur: " + temperature + "\n" + "°C" +
                "Luftfuktighet: " + humidity + "\n" + "%" +
                "Källa: " + origin;
    }
}
