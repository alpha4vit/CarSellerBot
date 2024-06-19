package by.gurinovich.carseller.carsellerbot.config;

import by.gurinovich.carseller.carsellerbot.bot.CarSellerBot;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotConfig {

    private final CarSellerBot carSellerBot;

    @EventListener(ContextRefreshedEvent.class)
    void init(){
        try {
            var bot = new TelegramBotsApi(DefaultBotSession.class);
            bot.registerBot(carSellerBot);
        } catch (TelegramApiException e) {
            log.error("Error while registering bot");
            throw new RuntimeException(e);
        }
    }

}
