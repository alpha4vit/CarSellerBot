package by.gurinovich.carseller.carsellerbot.keyboards;

import by.gurinovich.carseller.carsellerbot.service.CarBrandService;
import by.gurinovich.carseller.carsellerbot.service.CarModelService;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.CarActions;
import by.gurinovich.carseller.carsellerbot.utils.enums.actions.GlobalActions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarActionKeyboardGenerator {

    private final CarBrandService carBrandService;
    private final CarModelService carModelService;

    private static final InlineKeyboardButton BACK_BRAND_BUTTON = new InlineKeyboardButton("В главное меню!");
    private static final InlineKeyboardButton BACK_MODEL_BUTTON = new InlineKeyboardButton("Назад!");
    private static final InlineKeyboardButton NEXT_BRAND_BUTTON = new InlineKeyboardButton("➡️");
    private static final InlineKeyboardButton PREVIOUS_BRAND_BUTTON = new InlineKeyboardButton("⬅️");

    static {
        BACK_BRAND_BUTTON.setCallbackData(GlobalActions.GLOBAL.name());
        BACK_MODEL_BUTTON.setCallbackData(GlobalActions.GLOBAL_SEARCH.name());
        NEXT_BRAND_BUTTON.setCallbackData(CarActions.BRAND_NEXT_BUTTON.name());
        PREVIOUS_BRAND_BUTTON.setCallbackData(CarActions.BRAND_PREVIOUS_BUTTON.name());
    }

    public InlineKeyboardMarkup getBrandsMarkup(Long page){
        var brands = carBrandService.getAllOrderedByName(page);
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        brands.forEach(brand -> {
            var button = new InlineKeyboardButton(brand.getName());
            button.setCallbackData(String.format("CAR_BRAND_%s", brand.getId()));
            keyboard.add(List.of(button));
        });
        keyboard.add(List.of(PREVIOUS_BRAND_BUTTON, NEXT_BRAND_BUTTON));
        keyboard.add(List.of(BACK_BRAND_BUTTON));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getModelsMarkup(Long page, Long brandId)
    {
        var brand = carBrandService.getById(brandId);
        var models = carModelService.getByBrandOrderedByName(page, brand);
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        models.forEach(model -> {
            var button = new InlineKeyboardButton(model.getName());
            button.setCallbackData(String.format("CAR_MODEL_%s", model.getId()));
            keyboard.add(List.of(button));
        });
        keyboard.add(List.of(BACK_MODEL_BUTTON));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

}
