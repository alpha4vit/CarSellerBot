package by.gurinovich.carseller.carsellerbot.handler.impl;

import by.gurinovich.carseller.carsellerbot.entity.ReviewEntity;
import by.gurinovich.carseller.carsellerbot.handler.VideoNoteHandler;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class VideoNoteHandlerImpl implements VideoNoteHandler {

    private final ReviewService reviewService;

    @Override
    public SendVideoNote handleVideoNote(Message message) {
        var review = ReviewEntity.builder()
                .videoId(message.getVideoNote().getFileId())
                .build();
        reviewService.save(review);
        var sendVideoNote = new SendVideoNote();
        sendVideoNote.setChatId(message.getChatId());
        sendVideoNote.setVideoNote(new InputFile(message.getVideoNote().getFileId()));
        return sendVideoNote;
    }
}
