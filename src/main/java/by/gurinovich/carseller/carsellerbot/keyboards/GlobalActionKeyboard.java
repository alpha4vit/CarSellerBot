package by.gurinovich.carseller.carsellerbot.keyboards;

import by.gurinovich.carseller.carsellerbot.utils.enums.actions.GlobalActions;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class GlobalActionKeyboard {
    private static final InlineKeyboardButton SEARCH_BUTTON = new InlineKeyboardButton("Найти авто!");
    private static final InlineKeyboardButton REVIEWS_BUTTON = new InlineKeyboardButton("Отзывы!");


    static {
        SEARCH_BUTTON.setCallbackData(GlobalActions.GLOBAL_SEARCH.name());
        REVIEWS_BUTTON.setCallbackData(GlobalActions.GLOBAL_REVIEW.name());
    }

    public static InlineKeyboardMarkup actionChooseButtons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = List.of(SEARCH_BUTTON, REVIEWS_BUTTON);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        buttons.forEach(el -> keyboard.add(List.of(el)));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

}
