package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "update users set bot_state= :bot_state where chat_id = :chat_id")
    void updateBotStateByChatId(@Param("chat_id") Long chatId, @Param("bot_state") int botState);

    @Modifying
    @Query(value = "update users set some_integer_field = some_integer_field + 1 where chat_id = :chat_id", nativeQuery = true)
    void incrementSomeIntegerFieldByChatId(@Param("chat_id") Long chatId);
}
