package com.BotifyYou_bot.BotifyYou_bot.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSession {
    private BotState.State botState;

    public UserSession() {
        this.botState = BotState.State.DEFAULT;
    }

}
