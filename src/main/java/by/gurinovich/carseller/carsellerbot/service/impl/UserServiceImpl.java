package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.repository.CarBrandRepository;
import by.gurinovich.carseller.carsellerbot.repository.ReviewRepository;
import by.gurinovich.carseller.carsellerbot.repository.UserRepository;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import by.gurinovich.carseller.carsellerbot.utils.enums.PageType;
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
    private final CarBrandRepository carBrandRepository;

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
    public UserEntity resetActualPageNum(Long chatId, PageType pageType) {
        var userEntity = getByChatId(chatId);
        switch (pageType){
            case REVIEW -> userEntity.setReviewNum(0L);
            case CAR_BRAND -> userEntity.setBrandPageNum(0L);
            case CAR_MODEL -> userEntity.setModelPageNum(0L);
//            case CAR_GENERATION -> userEntity.setG(0L);
        }
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity incActualPageNum(Long chatId, PageType pageType) {
        var userEntity = getByChatId(chatId);
        switch (pageType){
            case REVIEW -> {
                var val = userEntity.getReviewNum() + 1;
                var reviewCount = reviewRepository.findCount();
                if (val >= reviewCount)
                    userEntity.setReviewNum(0L);
                else if (reviewCount > val)
                    userEntity.setReviewNum(val);
            }
            case CAR_BRAND -> {
                var val = userEntity.getBrandPageNum() + 1;
                var brandCount = carBrandRepository.findCount();
                if (val >= brandCount)
                    userEntity.setBrandPageNum(0L);
                else if (brandCount > val)
                    userEntity.setBrandPageNum(val);
            }
        }
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity decActualPageNum(Long chatId, PageType pageType) {
        var userEntity = getByChatId(chatId);
        switch (pageType){
            case REVIEW -> {
                var val = userEntity.getReviewNum() - 1;
                var reviewCount = reviewRepository.findCount();
                if (val >= reviewCount)
                    userEntity.setReviewNum(0L);
                else if (reviewCount > val)
                    userEntity.setReviewNum(val);
            }
            case CAR_BRAND -> {
                var val = userEntity.getBrandPageNum() - 1;
                var brandCount = carBrandRepository.findCount();
                if (val >= brandCount)
                    userEntity.setBrandPageNum(0L);
                else if (brandCount > val)
                    userEntity.setBrandPageNum(val);
            }
        }
        return userRepository.save(userEntity);
    }


}
