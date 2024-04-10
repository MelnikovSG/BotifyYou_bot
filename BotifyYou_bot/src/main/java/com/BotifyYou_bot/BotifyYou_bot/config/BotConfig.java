package com.BotifyYou_bot.BotifyYou_bot.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("BotifyYou_bot")
    String botName;
    @Value("5905984080:AAGSWCoQQ1gQWogoca63ByYCKNOQOComufE")
    String botToken;
    @Value("73f337d01e1bdcffef4be8f8e34ee388")
    String apiKey;
}
