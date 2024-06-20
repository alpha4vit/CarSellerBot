package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.handler.GlobalActionHandler;
import by.gurinovich.carseller.carsellerbot.handler.MessageHandler;
import by.gurinovich.carseller.carsellerbot.keyboards.GlobalActionKeyboard;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.GlobalActions;
import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;
import by.gurinovich.carseller.carsellerbot.utils.parsers.HTMLParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {

    private final UserService userService;
    private final ReviewService reviewService;
    private final HTMLParser htmlParser;
    private final GlobalActionHandler globalActionHandler;

    @Override
    @SneakyThrows
    public void handleMessage(AbsSender sender, Message message) {
        switch (message.getText()) {
            case "/start" -> {
                userService.save(UserEntity.builder()
                        .chatId(message.getChatId())
                        .name(message.getFrom().getFirstName())
                        .username(message.getFrom().getUserName())
                        .botState(BotState.START)
                        .build());
                var response = htmlParser.readHTML("src/main/resources/static/preview.html");
                sender.executeAsync(SendMessage.builder()
                        .chatId(message.getChatId())
                        .text(response)
                        .parseMode("HTML")
                        .replyMarkup(GlobalActionKeyboard.actionChooseButtons())
                        .build()
                );
            }
            case "/video" -> reviewService.getAll().stream()
                    .map(review -> {
                        var sendVideoNote = new SendVideoNote();
                        sendVideoNote.setChatId(message.getChatId());
                        sendVideoNote.setVideoNote(new InputFile(review.getVideoId()));
                        return sendVideoNote;
                    })
                    .forEach(sender::executeAsync);
            case "/reviews" -> {
                var mockMessage = new Message();
                var chat = new Chat();
                var callback = new CallbackQuery();
                chat.setId(message.getChatId());
                mockMessage.setChat(chat);
                callback.setMessage(message);
                callback.setData(GlobalActions.GLOBAL_REVIEW.toString());
                globalActionHandler.handleCallback(sender, callback);
            }
            default ->
                    sender.executeAsync(new SendMessage(message.getChatId().toString(), "Данное действие пока не поддерживается"));
        }
    }
}
