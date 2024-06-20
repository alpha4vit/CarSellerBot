package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
