package se.iths.weatherforecast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.weatherforecast.dao.MetClient;
import se.iths.weatherforecast.dao.MeteoClient;
import se.iths.weatherforecast.dao.SmhiClient;
import se.iths.weatherforecast.entity.Weather;

@Service
public class WeatherService {

    @Autowired
    SmhiClient smhiClient;
    @Autowired
    MetClient metClient;
    @Autowired
    MeteoClient meteoClient;

    public Weather getBestWeather() {
        Weather smhi = smhiClient.getSmhiWeather();
        Weather met = metClient.getMetWeather();
        Weather meteo = meteoClient.getMeteoWeather();

        if (smhi.getTemperature() > met.getTemperature()
                && smhi.getTemperature() > meteo.getTemperature()) {
            return smhi;
        } else if (met.getTemperature() > smhi.getTemperature() &&
                met.getTemperature() > meteo.getTemperature()) {
            return met;
        } else {
            return meteo;
        }
    }

}
