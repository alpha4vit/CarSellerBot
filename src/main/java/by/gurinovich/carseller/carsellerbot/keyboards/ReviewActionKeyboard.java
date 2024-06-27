package by.gurinovich.carseller.carsellerbot.keyboards;

import by.gurinovich.carseller.carsellerbot.utils.enums.actions.GlobalActions;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.ReviewActions;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ReviewActionKeyboard {
    private static final InlineKeyboardButton CREATE_REVIEW_BUTTON = new InlineKeyboardButton("Добавить отзыв!");
    private static final InlineKeyboardButton GET_REVIEWS_BUTTON = new InlineKeyboardButton("Получить список отзывов!");
    private static final InlineKeyboardButton NEXT_REVIEW_BUTTON = new InlineKeyboardButton("➡️");
    private static final InlineKeyboardButton PREVIOUS_REVIEW_BUTTON = new InlineKeyboardButton("⬅️");
    private static final InlineKeyboardButton GO_BACK_BUTTON = new InlineKeyboardButton("Назад!");
    private static final InlineKeyboardButton MAIN_MENU_BUTTON = new InlineKeyboardButton("В главное меню!");

    static {
        NEXT_REVIEW_BUTTON.setCallbackData(ReviewActions.REVIEW_NEXT_BUTTON.name());
        PREVIOUS_REVIEW_BUTTON.setCallbackData(ReviewActions.REVIEW_PREVIOUS_BUTTON.name());
        CREATE_REVIEW_BUTTON.setCallbackData(ReviewActions.REVIEW_CREATE_BUTTON.name());
        GET_REVIEWS_BUTTON.setCallbackData(ReviewActions.REVIEW_GET_BUTTON.name());
        GO_BACK_BUTTON.setCallbackData(GlobalActions.GLOBAL_REVIEW.name());
        MAIN_MENU_BUTTON.setCallbackData(GlobalActions.GLOBAL.name());
    }

    public static InlineKeyboardMarkup slideButtons(){
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();
        var buttons = List.of(PREVIOUS_REVIEW_BUTTON, NEXT_REVIEW_BUTTON);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(buttons);
        keyboard.add(List.of(GO_BACK_BUTTON));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup actionChooseButtons(){
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();
        var buttons = List.of(CREATE_REVIEW_BUTTON, GET_REVIEWS_BUTTON);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        buttons.forEach(el -> keyboard.add(List.of(el)));
        keyboard.add(List.of(MAIN_MENU_BUTTON));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

}
