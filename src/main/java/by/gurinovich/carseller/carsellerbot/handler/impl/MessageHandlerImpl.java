package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.handler.GlobalActionHandler;
import by.gurinovich.carseller.carsellerbot.handler.MessageHandler;
import by.gurinovich.carseller.carsellerbot.handler.ReviewHandler;
import by.gurinovich.carseller.carsellerbot.keyboards.GlobalActionKeyboard;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.GlobalActions;
import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;
import by.gurinovich.carseller.carsellerbot.utils.parsers.HTMLParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {

    private final UserService userService;
    private final HTMLParser htmlParser;
    private final GlobalActionHandler globalActionHandler;
    private final ReviewHandler reviewHandler;

    @Override
    @SneakyThrows
    public void handleMessage(AbsSender sender, Message message) {
        switch (message.getText()) {
            case "/start" -> {
                var user = UserEntity.builder()
                        .chatId(message.getChatId())
                        .name(message.getFrom().getFirstName())
                        .username(message.getFrom().getUserName())
                        .botState(BotState.START)
                        .build();
                userService.save(user);
                var response = htmlParser.readHTML("src/main/resources/static/preview.html");
                var messageResponse = SendMessage.builder()
                        .chatId(message.getChatId())
                        .text(response).parseMode("HTML")
                        .replyMarkup(GlobalActionKeyboard.actionChooseButtons())
                        .build();
                sender.executeAsync(messageResponse);
            }
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
            default -> {
                var user = userService.getByChatId(message.getChatId());
                switch (user.getBotState()) {
                    case REVIEW -> {
                        reviewHandler.saveReviewTest(sender, message);
                        userService.updateChatState(user.getChatId(), BotState.START);
                    }
                    default ->
                            sender.executeAsync(new SendMessage(message.getChatId().toString(), "Данное действие пока не поддерживается"));
                }
            }
        }
    }
}
