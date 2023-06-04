package se.iths.weatherforecast.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.iths.weatherforecast.dao.smhi.Parameter;
import se.iths.weatherforecast.dao.smhi.Smhi;
import se.iths.weatherforecast.entity.Weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class SmhiClient {

    WebClient client = WebClient.create("https://opendata-download-metfcst.smhi.se");

    public Weather getSmhiWeather() {
        Mono<Smhi> smhi = client
                .get()
                .uri("/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json")
                .retrieve()
                .bodyToMono(Smhi.class);
        Smhi s = smhi.block();

        List<Parameter> parameters = s.getTimeSeries().get(26).getParameters();
        double temperature = 0;
        double humidity = 0;

        for (Parameter p : parameters) {
            if (p.getName().equals("t")) {
                temperature = p.getValues().get(0);
            }
            if (p.getName().equals("r")) {
                humidity = p.getValues().get(0);
            }
        }

        LocalDateTime timeStamp = LocalDateTime
                .parse(s.getTimeSeries().get(26).getValidTime(), DateTimeFormatter.ISO_DATE_TIME);

        String origin = "Smhi";

        System.out.println();
        System.out.println("Tid: " + timeStamp);
        System.out.println("Temperatur: " + temperature + "°C");
        System.out.println("Luftfuktighet: " + humidity + "%");
        System.out.println("Källa: " + origin);

        return new Weather(timeStamp, temperature, humidity, origin);
    }

}
