package com.fez.chong.tg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

// refs: https://medium.com/@Mumuksia/telegram-bot-with-java-and-docker-636c4136fe7b

@Slf4j
@Component
public class UkrGreetBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "UkrGreetingBot";
    }

    @Override
    public String getBotToken() {
        return "5765604681:AAHPt7uGRmksuQQkp8s7a9jpMZvTG38m0lc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message msg = update.getMessage();
            Long chatId = msg.getChatId();
            try {
                String reply = msg.getText().contains("Glory to Ukraine")?"Glory to the heroes":"Glory to Ukraine";
                sendNotification(String.valueOf(chatId), reply);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void sendNotification(String chatId, String msg) throws TelegramApiException {
        SendMessage response = new SendMessage(chatId, msg);
        execute(response);
    }
}
