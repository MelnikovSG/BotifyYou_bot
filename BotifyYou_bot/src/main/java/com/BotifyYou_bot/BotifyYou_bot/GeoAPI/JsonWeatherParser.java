package com.BotifyYou_bot.BotifyYou_bot.GeoAPI;

import com.BotifyYou_bot.BotifyYou_bot.Entity.Weather;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonWeatherParser {
    private Weather weath = new Weather();
    public String city;
    public String weather;
    public int temperature;

    public void parseWeather(String jsonString) {
        try {
            JSONObject obj = new JSONObject(jsonString);
            city = obj.getString("name");
            weather = obj.getJSONArray("weather")
                    .getJSONObject(0).getString("description");
            temperature = obj.getJSONObject("main").getInt("temp");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        weath.setCity(city);
        weath.setWeather(weather);
        weath.setTemperature(temperature);
    }
}
