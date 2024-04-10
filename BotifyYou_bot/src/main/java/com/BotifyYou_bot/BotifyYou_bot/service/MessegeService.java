package com.BotifyYou_bot.BotifyYou_bot.service;

import com.BotifyYou_bot.BotifyYou_bot.Entity.Weather;
import com.BotifyYou_bot.BotifyYou_bot.GeoAPI.CitySearch;
import com.BotifyYou_bot.BotifyYou_bot.GeoAPI.JsonWeatherParser;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessegeService {

    TransliterationService transliteration = new TransliterationService();
    CitySearch citySearch = new CitySearch();
    JsonWeatherParser jsonWeatherParser = new JsonWeatherParser();
    Weather weather = new Weather();

    public void startCommandReceived(AbsSender absSender, Long chatId, String firstName) {
        String answer = "Привет, " + firstName + ", да прибудет с тобой сила!";
        sendMessage(absSender, chatId, answer);
    }

    public void getWeather(AbsSender absSender, Long chatId) {
        String question = "Смотрю ты хочешь узнать погоду, какой город интересует?";
        sendMessage(absSender, chatId, question);
    }

    public void sendMessage(AbsSender absSender, Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }

    public void handleCityInput(AbsSender absSender, Long chatId, String apiKey, String city) {
        String transCity = transliteration.translate(city);
        String JSonCity = citySearch.findCity(apiKey, transCity);
        jsonWeatherParser.parseWeather(JSonCity);
        weather.setWeather(jsonWeatherParser.weather);
        weather.setTemperature(jsonWeatherParser.temperature);
        sendMessage(absSender, chatId, "В городе - " + city + " сегодня - "
                + weather.getWeather() + " температура воздуха: " + weather.getTemperature() + " градусов");
    }
}
