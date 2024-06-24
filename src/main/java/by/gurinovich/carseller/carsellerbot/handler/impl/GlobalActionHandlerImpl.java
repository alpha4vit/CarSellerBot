package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.GlobalActionHandler;
import by.gurinovich.carseller.carsellerbot.keyboards.GlobalActionKeyboard;
import by.gurinovich.carseller.carsellerbot.keyboards.ReviewActionKeyboard;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.GlobalActions;
import by.gurinovich.carseller.carsellerbot.utils.parsers.HTMLParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
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
        var chatId = callbackQuery.getMessage().getChatId();
        var messageId = callbackQuery.getMessage().getMessageId();
        switch (GlobalActions.valueOf(callbackQuery.getData())){
            case GLOBAL -> {
                var message = htmlParser.readHTML("src/main/resources/static/preview.html");
                var messageResponse = EditMessageText.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .text(message)
                        .parseMode("HTML")
                        .replyMarkup(GlobalActionKeyboard.actionChooseButtons())
                        .build();
                sender.executeAsync(messageResponse);
            }
            case GLOBAL_REVIEW -> {
                var message = String.format(htmlParser.readHTML("src/main/resources/static/review/reviewsTab.html"), reviewService.getAllCount());
                if (callbackQuery.getMessage().getText() == null){
                    var deleteMessage = DeleteMessage.builder()
                            .messageId(callbackQuery.getMessage().getMessageId())
                            .chatId(callbackQuery.getMessage().getChatId())
                            .build();
                    var messageResponse = SendMessage.builder()
                            .chatId(callbackQuery.getMessage().getChatId())
                            .parseMode("HTML")
                            .text(message)
                            .replyMarkup(ReviewActionKeyboard.actionChooseButtons())
                            .build();
                    sender.executeAsync(messageResponse);
                    sender.executeAsync(deleteMessage);
                }
                else {
                    var messageResponse = EditMessageText.builder()
                            .chatId(chatId)
                            .messageId(messageId)
                            .parseMode("HTML")
                            .text(message)
                            .replyMarkup(ReviewActionKeyboard.actionChooseButtons())
                            .build();
                    sender.executeAsync(messageResponse);
                }
            }
        }
    }
}
