package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.CarHandler;
import by.gurinovich.carseller.carsellerbot.keyboards.CarActionKeyboardGenerator;
import by.gurinovich.carseller.carsellerbot.service.CarBrandService;
import by.gurinovich.carseller.carsellerbot.service.CarModelService;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import by.gurinovich.carseller.carsellerbot.utils.enums.PageType;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.CarActions;
import by.gurinovich.carseller.carsellerbot.utils.parsers.CallbackDataParser;
import by.gurinovich.carseller.carsellerbot.utils.parsers.HTMLParser;
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
    private final UserService userService;
    private final CarBrandService carBrandService;
    private final HTMLParser htmlParser;
    private final CarModelService carModelService;

    @Override
    @SneakyThrows
    public void handleCallback(AbsSender sender, CallbackQuery callbackQuery) {
        var chatId = callbackQuery.getMessage().getChatId();
        var messageId = callbackQuery.getMessage().getMessageId();
        var user = userService.getByChatId(chatId);
        switch (CarActions.fromString(callbackQuery.getData())){
            case CAR_BRAND_NEXT_BUTTON -> {
                var message = htmlParser.readHTML("src/main/resources/static/car/brandTab.html");
                var pagesCount = carBrandService.getPagesCount();
                user = userService.incActualPageNum(chatId, PageType.CAR_BRAND, pagesCount);
                var messageResponse = EditMessageText.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .replyMarkup(carActionKeyboardGenerator.getBrandsMarkup(user.getBrandPageNum()))
                        .text(message)
                        .parseMode("HTML")
                        .build();
                sender.executeAsync(messageResponse);
            }
            case CAR_BRAND_PREVIOUS_BUTTON -> {
                var message = htmlParser.readHTML("src/main/resources/static/car/brandTab.html");
                var pagesCount = carBrandService.getPagesCount();
                user = userService.decActualPageNum(chatId, PageType.CAR_BRAND, pagesCount);
                var messageResponse = EditMessageText.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .replyMarkup(carActionKeyboardGenerator.getBrandsMarkup(user.getBrandPageNum()))
                        .text(message)
                        .parseMode("HTML")
                        .build();
                sender.executeAsync(messageResponse);
            }
            case CAR_MODEL_NEXT_BUTTON -> {
                var brandId = callbackDataParser.parseBrandIdFromModelMessage(callbackQuery.getMessage().getText());
                var pagesCount = carModelService.getPagesCountByBrandId(brandId);
                var message = String.format(
                        htmlParser.readHTML("src/main/resources/static/car/modelTab.html"),
                        brandId);
                user = userService.incActualPageNum(chatId, PageType.CAR_MODEL, pagesCount);
                var messageResponse = EditMessageText.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .replyMarkup(carActionKeyboardGenerator.getModelsMarkup(user.getModelPageNum(), brandId))
                        .text(message)
                        .parseMode("HTML")
                        .build();
                sender.executeAsync(messageResponse);
            }
            case CAR_MODEL_PREVIOUS_BUTTON -> {
                var brandId = callbackDataParser.parseBrandIdFromModelMessage(callbackQuery.getMessage().getText());
                var pagesCount = carModelService.getPagesCountByBrandId(brandId);
                var message = String.format(
                        htmlParser.readHTML("src/main/resources/static/car/modelTab.html"),
                        brandId);
                user = userService.decActualPageNum(chatId, PageType.CAR_MODEL, pagesCount);
                var messageResponse = EditMessageText.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .replyMarkup(carActionKeyboardGenerator.getModelsMarkup(user.getModelPageNum(), brandId))
                        .text(message)
                        .parseMode("HTML")
                        .build();
                sender.executeAsync(messageResponse);
            }
            case null -> {
                var itemId = callbackDataParser.parseId(callbackQuery.getData());
                var messageResponse = EditMessageText.builder()
                        .chatId(chatId)
                        .messageId(messageId);
                if (callbackQuery.getData().startsWith("CAR_BRAND")) {
                    user = userService.resetActualPageNum(chatId, PageType.CAR_MODEL);
                    var messageText = String.format(
                            htmlParser.readHTML("src/main/resources/static/car/modelTab.html"),
                            itemId);
                    messageResponse.replyMarkup(
                            carActionKeyboardGenerator.getModelsMarkup(
                                    user.getModelPageNum(),
                                    itemId
                            ));
                    messageResponse.text(messageText);
                }
                else if (callbackQuery.getData().startsWith("CAR_MODEL")){
                    var messageText = htmlParser.readHTML("src/main/resources/static/car/generationTab.html");
                    messageResponse.replyMarkup(
                            carActionKeyboardGenerator.getGenerationMarkup(
                                    user.getModelPageNum(),
                                    itemId
                            ));
                    messageResponse.text(messageText);
                }
                sender.executeAsync(messageResponse.build());
            }
        }
    }
}
