package com.BotifyYou_bot.BotifyYou_bot.GeoAPI;

import com.BotifyYou_bot.BotifyYou_bot.config.BotConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component
public class CitySearch {

    public String findCity (String apiKey, String city){

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" +
                    city + "&appid=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Ошибка HTTP-кода: " + responseCode);
            } else {
                Scanner scanner1 = new Scanner(url.openStream());
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner1.hasNext()) {
                    stringBuilder.append(scanner1.nextLine());
                }
                scanner1.close();
                return stringBuilder.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
