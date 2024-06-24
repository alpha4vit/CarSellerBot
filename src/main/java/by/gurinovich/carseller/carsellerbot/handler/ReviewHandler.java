package by.gurinovich.carseller.carsellerbot.handler;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface ReviewHandler {
    void handleCallback(AbsSender sender, CallbackQuery callbackQuery);

    void getReviewsPage(AbsSender sender, CallbackQuery callbackQuery);

    void saveReviewTest(AbsSender sender, Message message);
}
