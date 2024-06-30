package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import by.gurinovich.carseller.carsellerbot.repository.UserRepository;
import by.gurinovich.carseller.carsellerbot.service.UserService;
import by.gurinovich.carseller.carsellerbot.utils.enums.PageType;
import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    @Override
    @Transactional
    public void updateChatState(Long chatId, BotState state) {
        userRepository.updateBotStateByChatId(chatId, state.ordinal());
    }

    @Override
    public UserEntity resetActualPageNum(Long chatId, PageType pageType) {
        var userEntity = getByChatId(chatId);
        switch (pageType) {
            case REVIEW -> userEntity.setReviewNum(0L);
            case CAR_BRAND -> userEntity.setBrandPageNum(0L);
            case CAR_MODEL -> userEntity.setModelPageNum(0L);
            case CAR_GENERATION -> userEntity.setGenerationPageNum(0L);
        }
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity incActualPageNum(Long chatId, PageType pageType, Long pageCount) {
        var userEntity = getByChatId(chatId);
        switch (pageType) {
            case REVIEW -> {
                var val = userEntity.getReviewNum() + 1;
                if (val >= pageCount)
                    userEntity.setReviewNum(0L);
                else if (pageCount > val)
                    userEntity.setReviewNum(val);
            }
            case CAR_BRAND -> {
                var val = userEntity.getBrandPageNum() + 1;
                if (val >= pageCount)
                    userEntity.setBrandPageNum(0L);
                else if (pageCount > val)
                    userEntity.setBrandPageNum(val);
            }
            case CAR_MODEL -> {
                var val = userEntity.getModelPageNum() + 1;
                if (val >= pageCount)
                    userEntity.setModelPageNum(0L);
                else if (pageCount > val)
                    userEntity.setModelPageNum(val);
            }
            case CAR_GENERATION -> {
                var val = userEntity.getGenerationPageNum() + 1;
                if (val >= pageCount)
                    userEntity.setGenerationPageNum(0L);
                else if (pageCount > val)
                    userEntity.setGenerationPageNum(val);
            }
        }
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity decActualPageNum(Long chatId, PageType pageType, Long pageCount) {
        var userEntity = getByChatId(chatId);
        switch (pageType) {
            case REVIEW -> {
                var val = userEntity.getReviewNum() - 1;
                if (val < 0)
                    userEntity.setReviewNum(pageCount - 1);
                else if (pageCount > val)
                    userEntity.setReviewNum(val);
            }
            case CAR_BRAND -> {
                var val = userEntity.getBrandPageNum() - 1;
                if (val < 0)
                    userEntity.setBrandPageNum(pageCount - 1);
                else if (pageCount > val)
                    userEntity.setBrandPageNum(val);
            }
            case CAR_MODEL -> {
                var val = userEntity.getModelPageNum() - 1;
                if (val < 0)
                    userEntity.setModelPageNum(pageCount - 1);
                else if (pageCount > val)
                    userEntity.setModelPageNum(val);
            }
            case CAR_GENERATION -> {
                var val = userEntity.getGenerationPageNum() - 1;
                if (val < 0)
                    userEntity.setGenerationPageNum(pageCount -1);
                else if (pageCount > val)
                    userEntity.setGenerationPageNum(val);
            }
        }
        return userRepository.save(userEntity);
    }
}
