package by.gurinovich.carseller.carsellerbot.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.VideoNote;

public interface VideoNoteHandler {
    SendVideoNote handleVideoNote(Message message);
}
