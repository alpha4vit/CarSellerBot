package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.handler.AnswerHandler;
import by.gurinovich.carseller.carsellerbot.handler.CallbackHandler;
import by.gurinovich.carseller.carsellerbot.handler.MessageHandler;
import by.gurinovich.carseller.carsellerbot.handler.VideoNoteHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaBotMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.VideoNote;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnswerHandlerImpl implements AnswerHandler {

    private final MessageHandler messageHandler;
    private final CallbackHandler callbackHandler;
    private final VideoNoteHandler videoNoteHandler;

    @Override
    @SneakyThrows
    public void answer(AbsSender sender, Message message) {
        log.info("message received");
        if (message.getVideoNote() != null){
            sender.execute(videoNoteHandler.handleVideoNote(message));
        }
        else {
            messageHandler.handleMessage(sender, message);
        }
    }


    @Override
    public void answer(AbsSender sender, CallbackQuery callbackQuery) {
        log.info("callback received");
        callbackHandler.handleCallback(callbackQuery);
    }
}