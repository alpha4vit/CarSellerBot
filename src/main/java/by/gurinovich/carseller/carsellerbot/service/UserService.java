package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.utils.enums.PageType;
import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;

public interface UserService {
    UserEntity save(UserEntity userEntity);

    UserEntity getByChatId(Long chatId);

    void updateChatState(Long chatId, BotState state);

    UserEntity resetActualPageNum(Long chatId, PageType pageType);

    UserEntity incActualPageNum(Long chatId, PageType pageType, Long pageCount);

    UserEntity decActualPageNum(Long chatId, PageType pageType, Long pageCount);

}
