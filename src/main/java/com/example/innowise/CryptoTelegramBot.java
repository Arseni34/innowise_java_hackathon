package com.example.innowise;

import com.example.innowise.config.BotConfig;
import com.example.innowise.model.CurrencyModel;
import com.example.innowise.service.CurrencyService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.ParseException;

@Component
@Slf4j
public class CryptoTelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public CryptoTelegramBot(BotConfig config) { this.config = config; }
    @Override
    public String getBotUsername() { return config.getBotName(); }
    @Override
    public String getBotToken() { return config.getToken(); }
    @Override
    public void onUpdateReceived(Update update) {
        CurrencyModel currencyModel = new CurrencyModel();
        String currency = "";

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/all":
                    try {
                        currency = CurrencyService.getCurrencyRate();

                    } catch (IOException e) {
                        sendMessage(chatId, "We have not found this currency." + "\n" +
                                "Enter the currency whose official exchange rate "  +
                                "you want to know" + "\n" );
                    } catch (ParseException e) {
                        throw new RuntimeException("Unable to parse date");
                    }
                    sendMessage(chatId, currency);                    break;

                default:
                    try {
                        currency = CurrencyService.getSearchedCurrencyRate(messageText);

                    } catch (IOException e) {
                        sendMessage(chatId, "We have not found this currency." + "\n" +
                                "Enter the currency whose official exchange rate " + "\n" +
                                "you want to know" + "\n" );
                    } catch (IndexOutOfBoundsException e) {
                        sendMessage(chatId, "We have not found this currency." + "\n" +
                                "Enter the currency whose official exchange rate "  +
                                "you want to know." + "\n");
                    } catch (ParseException e) {
                        throw new RuntimeException("Unable to parse date");
                    }
                    sendMessage(chatId, currency);
            }

//            Transaction transaction = null;
//            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//                // start a transaction
//                transaction = session.beginTransaction();
//                // save the student objects
//                session.persist(currencyModel);
//                // commit transaction
//                transaction.commit();
//            } catch (Exception e) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//                e.printStackTrace();
//            }
        }


    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Greetings, " + name + ", I'm the Telegram bot, you were searching for!" + "\n" +
                "Enter the currency whose official exchange rate" +
                "you want to know" + "\n";
        sendMessage(chatId, answer);
    }
    private void helpCommandReceived(Long chatId, String name) {
        String answer = "This bot will help to find out currencies of crypto coins. " +
                "The following commands are available to you:\n" +
                "/start - start the bot\n" +
                "/help - help menu"+
                "/all - first 100 coins"+
                "Write coin name to find its currency";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }


}
