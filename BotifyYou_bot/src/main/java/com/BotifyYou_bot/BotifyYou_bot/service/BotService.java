package com.BotifyYou_bot.BotifyYou_bot.service;

import com.BotifyYou_bot.BotifyYou_bot.config.BotConfig;
import com.BotifyYou_bot.BotifyYou_bot.config.BotState;
import com.BotifyYou_bot.BotifyYou_bot.config.UserSession;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Component
public class BotService extends TelegramLongPollingBot {

    final BotConfig config;
    final MessegeService messegeService;

    public BotService(BotConfig config) {
        this.config = config;
        this.messegeService = new MessegeService();
    }
    private final Map<Long, UserSession> userSessions = new HashMap<>();

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

            UserSession userSession = userSessions.computeIfAbsent(chatId, k -> new UserSession());

            if(userSession.getBotState() == BotState.State.AWAITING_CITY_NAME){
                messegeService.handleCityInput(this, chatId, config.getApiKey(), message);
                userSession.setBotState(BotState.State.DEFAULT);
            }else {
                switch (message) {
                    case "/start":
                        messegeService.startCommandReceived(this, chatId, update.getMessage().getChat().getFirstName());
                        break;
                    case "/weather":
                        messegeService.getWeather(this, chatId);
                        userSession.setBotState(BotState.State.AWAITING_CITY_NAME);
                        break;
                    default:
                        messegeService.sendMessage(this, chatId, "Контент в разработке");
                        break;
                }
            }
        }

    }
}
