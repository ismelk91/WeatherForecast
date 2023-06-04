package se.iths.weatherforecast.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.iths.weatherforecast.dao.met.Met;
import se.iths.weatherforecast.entity.Weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class MetClient {

    WebClient client = WebClient.create("https://api.met.no");

    public Weather getMetWeather() {
        Mono<Met> met = client
                .get()
                .uri("/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300")
                .retrieve()
                .bodyToMono(Met.class);
        Met m = met.block();

        double temperature = m.getProperties().getTimeseries().get(27).getData().getInstant().getDetails().getAirTemperature();
        double humidity = m.getProperties().getTimeseries().get(27).getData().getInstant().getDetails().getRelativeHumidity();
        LocalDateTime timeStamp = LocalDateTime.parse(m.getProperties().getTimeseries().get(27).getTime(), DateTimeFormatter.ISO_DATE_TIME);
        String origin = "Met";

        System.out.println();
        System.out.println("Tid: " + timeStamp);
        System.out.println("Temperatur: " + temperature + "°C");
        System.out.println("Luftfuktighet: " + humidity + "%");
        System.out.println("Källa: " + origin);

        return new Weather(timeStamp, temperature, humidity, origin);
    }

}
