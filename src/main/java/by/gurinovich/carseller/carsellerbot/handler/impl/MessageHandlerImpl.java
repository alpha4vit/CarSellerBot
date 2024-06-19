package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.MessageHandler;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {

    private final ReviewService reviewService;

    @Override
    @SneakyThrows
    public void handleMessage(AbsSender sender, Message message) {
        switch (message.getText()){
            case "/video" ->
                reviewService.getAll().stream()
                        .map(review -> {
                            var sendVideoNote = new SendVideoNote();
                            sendVideoNote.setChatId(message.getChatId());
                            sendVideoNote.setVideoNote(new InputFile(review.getVideoId()));
                            return sendVideoNote;
                        })
                        .forEach(note -> {
                            try {
                                sender.execute(note);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        });
            default -> sender.execute(new SendMessage(message.getChatId().toString(), "Данное действие пока не поддерживается"));
        }
    }
}
