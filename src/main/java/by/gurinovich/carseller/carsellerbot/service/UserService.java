package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;

public interface UserService {
    UserEntity save(UserEntity userEntity);

    UserEntity getByChatId(Long chatId);

    void updateChatState(Long chatId, BotState state);

    UserEntity resetActualReviewNum(Long chatId);

    UserEntity incActualReviewNum(Long chatId);

    UserEntity decActualReviewNum(Long chatId);
}
