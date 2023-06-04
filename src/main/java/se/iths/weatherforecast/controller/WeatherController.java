package se.iths.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.weatherforecast.service.WeatherService;

@Controller
public class WeatherController {

    @Autowired
    WeatherService service;

    @GetMapping("/weather")
    public String getWeather(Model m) {
        m.addAttribute("bestweather", service.getBestWeather());
        return "weather";
    }

}
