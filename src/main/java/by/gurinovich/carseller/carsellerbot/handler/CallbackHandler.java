package by.gurinovich.carseller.carsellerbot.handler;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackHandler {
    BotApiMethodMessage handleCallback(CallbackQuery callbackQuery);
}
