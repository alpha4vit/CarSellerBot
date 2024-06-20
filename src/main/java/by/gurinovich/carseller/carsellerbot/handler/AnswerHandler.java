package by.gurinovich.carseller.carsellerbot.handler;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface AnswerHandler{
    void answer(AbsSender sender, Message message);

    void answer(AbsSender sender, CallbackQuery callbackQuery);

}
