package by.gurinovich.carseller.carsellerbot.handler;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface GlobalActionHandler {

    void handleCallback(AbsSender sender, CallbackQuery callbackQuery);
}
