package se.iths.weatherforecast.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.weatherforecast.entity.Weather;
import se.iths.weatherforecast.service.WeatherService;

@RestController
@RequestMapping("/rest")
public class WeatherRestController {

    @Autowired
    WeatherService service;

    @GetMapping("/weather")
    public Weather getWeather() {
        return service.getBestWeather();
    }

}
