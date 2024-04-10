package com.BotifyYou_bot.BotifyYou_bot.service;

import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessegeService {

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

    public void handleCityInput(AbsSender absSender, Long chatId, String city) {
        sendMessage(absSender, chatId, "Работаем с городом: " + city);
    }
}
