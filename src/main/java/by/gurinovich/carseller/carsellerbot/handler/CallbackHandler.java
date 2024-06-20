package by.gurinovich.carseller.carsellerbot.handler;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface CallbackHandler {
    BotApiMethodMessage handleCallback(AbsSender sender, CallbackQuery callbackQuery);
}
