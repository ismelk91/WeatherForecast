package se.iths.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.iths.weatherforecast.service.WeatherService;

@Controller
public class WeatherController {

    @Autowired
    WeatherService service;

}
