package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.repository.ReviewRepository;
import by.gurinovich.carseller.carsellerbot.repository.UserRepository;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getByChatId(Long chatId) {
        return userRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("User with this chatId not found!"));
    }

    @Override
    @Transactional
    public void updateChatState(Long chatId, BotState state) {
        userRepository.updateBotStateByChatId(chatId, state.ordinal());
    }

    @Override
    @Transactional
    public UserEntity resetActualReviewNum(Long chatId) {
        var userEntity = getByChatId(chatId);
        userEntity.setReviewNum(0L);
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity incActualReviewNum(Long chatId) {
        var userEntity = getByChatId(chatId);
        var val = userEntity.getReviewNum() + 1;
        var reviewCount = reviewRepository.findCount();
        if (val >= reviewCount)
            userEntity.setReviewNum(0L);
        else if (reviewCount > val)
            userEntity.setReviewNum(val);
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity decActualReviewNum(Long chatId) {
        var userEntity = getByChatId(chatId);
        var val = userEntity.getReviewNum() - 1;
        var reviewCount = reviewRepository.findCount();
        if (userEntity.getReviewNum() == 0){
            userEntity.setReviewNum(reviewCount-1);
        }
        else if (reviewCount > val)
            userEntity.setReviewNum(val);
        return userRepository.save(userEntity);
    }


}
