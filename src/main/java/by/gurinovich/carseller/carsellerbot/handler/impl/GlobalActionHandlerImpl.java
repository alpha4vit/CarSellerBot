package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.GlobalActionHandler;
import by.gurinovich.carseller.carsellerbot.keyboards.ReviewActionKeyboard;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.GlobalActions;
import by.gurinovich.carseller.carsellerbot.utils.parsers.HTMLParser;
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
public class GlobalActionHandlerImpl implements GlobalActionHandler {

    private final HTMLParser htmlParser;
    private final ReviewService reviewService;

    @Override
    @SneakyThrows
    public void handleCallback(AbsSender sender, CallbackQuery callbackQuery) {
        switch (GlobalActions.valueOf(callbackQuery.getData())){
            case GLOBAL_REVIEW -> {
                var message = String.format(htmlParser.readHTML("src/main/resources/static/reviewsTab.html"), reviewService.getAllCount());
                var messageResponse = SendMessage.builder()
                        .chatId(callbackQuery.getMessage().getChatId())
                        .parseMode("HTML")
                        .text(message)
                        .build();
                messageResponse.setReplyMarkup(ReviewActionKeyboard.actionChooseButtons());
                sender.executeAsync(messageResponse);
            }
        }
    }
}
