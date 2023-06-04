package se.iths.weatherforecast.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.iths.weatherforecast.dao.meteo.Meteo;
import se.iths.weatherforecast.entity.Weather;

import java.time.LocalDateTime;

@Configuration
public class MeteoClient {

    WebClient meteoClient = WebClient.create("https://api.open-meteo.com");

    public Weather getMeteoWeather() {
        Mono<Meteo> meteo = meteoClient
                .get()
                .uri("/v1/forecast?latitude=59.3110&longitude=18.0300&hourly=temperature_2m,relativehumidity_2m&forecast_days=3&timezone=auto")
                .retrieve()
                .bodyToMono(Meteo.class);
        Meteo m = meteo.block();

        LocalDateTime timeNextDay = LocalDateTime.now().withNano(0).withSecond(0).withMinute(0).plusHours(25);

        int index = -1;
        for (int i = 0; i < m.getHourly().getTime().size(); i++) {
            LocalDateTime meteoTime = LocalDateTime.parse(m.getHourly().getTime().get(i));
            if (meteoTime.equals(timeNextDay))
                index = i;
        }

        LocalDateTime timeStamp = LocalDateTime.parse(m.getHourly().getTime().get(index));
        double temperature = m.getHourly().getTemperature2m().get(index);
        double humidity = m.getHourly().getRelativehumidity2m().get(index);
        String origin = "Open Meteo";

        System.out.println();
        System.out.println("Tid: " + timeStamp);
        System.out.println("Temperatur: " + temperature + "°C");
        System.out.println("Luftfuktighet: " + humidity + "%");
        System.out.println("Källa: " + origin);

        return new Weather(timeStamp, temperature, humidity, origin);
    }

}








