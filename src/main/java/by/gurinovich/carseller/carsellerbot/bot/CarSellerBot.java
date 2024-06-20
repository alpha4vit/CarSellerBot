package by.gurinovich.carseller.carsellerbot.bot;

import by.gurinovich.carseller.carsellerbot.handler.AnswerHandler;
import by.gurinovich.carseller.carsellerbot.props.BotProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarSellerBot extends TelegramLongPollingBot {

    private final BotProperties botProperties;
    private final AnswerHandler answerHandler;

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null) {
            answerHandler.answer(this, update.getMessage());
        }
        else if (update.getCallbackQuery() != null)
            answerHandler.answer(this, update.getCallbackQuery());
    }

    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }

}
