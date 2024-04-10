package com.BotifyYou_bot.BotifyYou_bot.service;

import com.BotifyYou_bot.BotifyYou_bot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotService extends TelegramLongPollingBot {

    final BotConfig config;
    final MessegeService messegeService;

    public BotService(BotConfig config) {
        this.config = config;
        this.messegeService = new MessegeService();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (message){
                case "/start":
                    messegeService.startCommandReceived(this, chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/weather":
                    messegeService.getWeather(this, chatId, update.getMessage().getChat().getLocation());
                default:
                    messegeService.sendMessage(this, chatId, "Контент в разработке");
            }
        }

    }
}
