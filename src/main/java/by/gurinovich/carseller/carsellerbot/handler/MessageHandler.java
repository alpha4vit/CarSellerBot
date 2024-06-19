package by.gurinovich.carseller.carsellerbot.handler;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface MessageHandler {

    void handleMessage(AbsSender sender, Message message);

}
