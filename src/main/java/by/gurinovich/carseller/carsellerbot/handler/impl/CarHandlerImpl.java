package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.CarHandler;
import by.gurinovich.carseller.carsellerbot.keyboards.CarActionKeyboardGenerator;
import by.gurinovich.carseller.carsellerbot.utils.parsers.CallbackDataParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarHandlerImpl implements CarHandler {

    private final CarActionKeyboardGenerator carActionKeyboardGenerator;
    private final CallbackDataParser callbackDataParser;
    @Override
    @SneakyThrows
    public void handleCallback(AbsSender sender, CallbackQuery callbackQuery) {
        var chatId = callbackQuery.getMessage().getChatId();
        var messageId = callbackQuery.getMessage().getMessageId();
        if (callbackQuery.getData().startsWith("CAR_BRAND")){
            var brandId = callbackDataParser.parseId(callbackQuery.getData());
            var messageResponse = EditMessageText.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .text("Выберите нужную модель авто")
                    .replyMarkup(carActionKeyboardGenerator.getModelsMarkup(brandId))
                    .build();
            sender.executeAsync(messageResponse);
        }
    }
}
