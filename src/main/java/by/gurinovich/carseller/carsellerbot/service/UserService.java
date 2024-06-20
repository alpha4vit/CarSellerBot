package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;

public interface UserService {
    UserEntity save(UserEntity userEntity);

    UserEntity getByChatId(Long chatId);
}
