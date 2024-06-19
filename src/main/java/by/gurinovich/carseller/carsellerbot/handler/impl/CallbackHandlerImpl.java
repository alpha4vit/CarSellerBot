package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.CallbackHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CallbackHandlerImpl implements CallbackHandler {
    @Override
    public BotApiMethodMessage handleCallback(CallbackQuery callbackQuery) {
        return new SendMessage(callbackQuery.getMessage().getChatId().toString(), "callback");
    }
}
