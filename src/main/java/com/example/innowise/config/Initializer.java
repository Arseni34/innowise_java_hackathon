package com.example.innowise.config;

import com.example.innowise.CryptoTelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Component
public class Initializer {
    private final CryptoTelegramBot cryptoTelegramBot;
    @Autowired
    public Initializer(CryptoTelegramBot cryptoTelegramBot) {
        this.cryptoTelegramBot = cryptoTelegramBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init()throws TelegramApiException{
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try{
            telegramBotsApi.registerBot(cryptoTelegramBot);
        } catch (TelegramApiException e){

        }
    }
}