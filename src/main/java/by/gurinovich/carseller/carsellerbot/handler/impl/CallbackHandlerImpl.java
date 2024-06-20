package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.CallbackHandler;
import by.gurinovich.carseller.carsellerbot.handler.GlobalActionHandler;
import by.gurinovich.carseller.carsellerbot.handler.ReviewHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackHandlerImpl implements CallbackHandler {

    private final ReviewHandler reviewHandler;
    private final GlobalActionHandler globalActionHandler;

    @Override
    public BotApiMethodMessage handleCallback(AbsSender sender, CallbackQuery callbackQuery) {
        if (callbackQuery.getData().startsWith("REVIEW"))
            reviewHandler.handleCallback(sender, callbackQuery);
        else if (callbackQuery.getData().startsWith("GLOBAL"))
            globalActionHandler.handleCallback(sender, callbackQuery);
        return new SendMessage(callbackQuery.getMessage().getChatId().toString(), "callback");
    }
}
