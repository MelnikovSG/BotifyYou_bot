package com.BotifyYou_bot.BotifyYou_bot.service;

import com.ibm.icu.text.Transliterator;
import org.springframework.stereotype.Service;

@Service
public class TransliterationService {

    Transliterator transliterator = Transliterator.getInstance("Cyrillic-Latin");

    public String translate(String cityName) {
        String transName = transliterator.transliterate((cityName));
        return transName;
    }
}
