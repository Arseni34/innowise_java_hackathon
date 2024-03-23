package com.example.innowise.components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info")
    );

    String HELP_TEXT = "This bot will help to find out currencies of crypto coins. " +
            "The following commands are available to you:\n\n" +
            "/start - start the bot\n" +
            "/help - help menu"+
            "/all - first 200 coins"+
            "Write coin name to find its currency";

}