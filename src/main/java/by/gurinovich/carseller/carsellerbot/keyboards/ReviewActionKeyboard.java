package by.gurinovich.carseller.carsellerbot.keyboards;

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


    static {
        NEXT_REVIEW_BUTTON.setCallbackData(ReviewActions.NEXT_REVIEW_BUTTON.name());
        PREVIOUS_REVIEW_BUTTON.setCallbackData(ReviewActions.PREVIOUS_REVIEW_BUTTON.name());
        CREATE_REVIEW_BUTTON.setCallbackData(ReviewActions.CREATE_REVIEW_BUTTON.name());
        GET_REVIEWS_BUTTON.setCallbackData(ReviewActions.GET_REVIEWS_BUTTON.name());
    }

    public static InlineKeyboardMarkup slideButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = List.of(PREVIOUS_REVIEW_BUTTON, NEXT_REVIEW_BUTTON);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup actionChooseButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = List.of(CREATE_REVIEW_BUTTON, GET_REVIEWS_BUTTON);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        buttons.forEach(el -> keyboard.add(List.of(el)));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

}
