package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.entity.ReviewEntity;
import by.gurinovich.carseller.carsellerbot.handler.ReviewHandler;
import by.gurinovich.carseller.carsellerbot.keyboards.GlobalActionKeyboard;
import by.gurinovich.carseller.carsellerbot.keyboards.ReviewActionKeyboard;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import by.gurinovich.carseller.carsellerbot.utils.enums.ReviewType;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.ReviewActions;
import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;
import by.gurinovich.carseller.carsellerbot.utils.parsers.HTMLParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewHandlerImpl implements ReviewHandler {

    private final HTMLParser htmlParser;
    private final UserService userService;
    private final ReviewService reviewService;

    @Override
    @SneakyThrows
    public void handleCallback(AbsSender sender, CallbackQuery callbackQuery) {
        switch (ReviewActions.valueOf(callbackQuery.getData())) {
            case REVIEW_CREATE_BUTTON -> {
                var message = htmlParser.readHTML("src/main/resources/static/review/reviewCreateMessage.html");
                var messageResponse = EditMessageText.builder()
                        .text(message)
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .parseMode("HTML")
                        .chatId(callbackQuery.getMessage().getChatId())
                        .build();
                userService.updateChatState(callbackQuery.getMessage().getChatId(), BotState.REVIEW);
                sender.executeAsync(messageResponse);
            }
            case REVIEW_GET_BUTTON, REVIEW_NEXT_BUTTON, REVIEW_PREVIOUS_BUTTON -> {
                getReviewsPage(sender, callbackQuery);
            }
        }
    }

    @Override
    @SneakyThrows
    public void getReviewsPage(AbsSender sender, CallbackQuery callbackQuery) {
        var chatId = callbackQuery.getMessage().getChatId();
        var messageId = callbackQuery.getMessage().getMessageId();
        var page = 0L;
        switch (ReviewActions.valueOf(callbackQuery.getData())) {
            case REVIEW_GET_BUTTON -> {
                page = userService.resetActualReviewNum(chatId)
                        .getReviewNum();
            }
            case REVIEW_NEXT_BUTTON -> {
                page = userService.incActualReviewNum(chatId)
                        .getReviewNum();
            }
            case REVIEW_PREVIOUS_BUTTON -> {
                page = userService.decActualReviewNum(chatId)
                        .getReviewNum();
            }
        }
        var review = reviewService.getByPage(page)
                .getContent().getFirst();
        var deleteMessage = DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();
        switch (review.getReviewType()) {
            case TEXT -> {
                if (callbackQuery.getMessage().getVideoNote() != null) {
                    var message = SendMessage.builder()
                            .chatId(chatId)
                            .text(review.getValue())
                            .replyMarkup(ReviewActionKeyboard.slideButtons())
                            .build();
                    sender.executeAsync(message);
                    sender.executeAsync(deleteMessage);
                } else {
                    var message = EditMessageText.builder()
                            .chatId(chatId)
                            .messageId(messageId)
                            .text(review.getValue())
                            .replyMarkup(ReviewActionKeyboard.slideButtons())
                            .build();
                    sender.executeAsync(message);
                }

            }
            case VIDEO_MESSAGE -> {
                var videoMessage = SendVideoNote.builder()
                        .chatId(chatId)
                        .replyMarkup(ReviewActionKeyboard.slideButtons())
                        .videoNote(new InputFile(review.getVideoId()))
                        .build();
                sender.executeAsync(videoMessage);
                sender.executeAsync(deleteMessage);
            }
        }
    }

    @Override
    @SneakyThrows
    public void saveReviewTest(AbsSender sender, Message message) {
        ReviewEntity review = new ReviewEntity();
        if (message.getVideoNote() != null){
            review.setReviewType(ReviewType.VIDEO_MESSAGE);
            review.setVideoId(message.getVideoNote().getFileId());
        }
        else if (message.getText() != null){
            review.setReviewType(ReviewType.TEXT);
            review.setValue(message.getText());
        }
        reviewService.save(review);
        var response = SendMessage.builder()
                .chatId(message.getChatId())
                .text("Отзыв успешно сохранен!")
                .replyMarkup(GlobalActionKeyboard.actionChooseButtons())
                .build();
        sender.executeAsync(response);
    }

}
