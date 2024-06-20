package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.ReviewHandler;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.ReviewActions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewHandlerImpl implements ReviewHandler {

    @Override
    @SneakyThrows
    public void handleCallback(AbsSender sender, CallbackQuery callbackQuery) {
        log.info(callbackQuery.getData());
        switch (ReviewActions.valueOf(callbackQuery.getData())){
            case CREATE_REVIEW_BUTTON -> {
                sender.executeAsync(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "romaroma"));
            }
        }
    }

}
