package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.repository.UserRepository;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getByChatId(Long chatId) {
        return userRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("User with this chatId not found!"));
    }
}
