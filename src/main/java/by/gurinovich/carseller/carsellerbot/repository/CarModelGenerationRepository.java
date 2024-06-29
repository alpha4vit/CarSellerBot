package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelGenerationEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelGenerationRepository extends JpaRepository<CarModelGenerationEntity, Long> {

    List<CarModelGenerationEntity> findAllByCarModelEntity(CarModelEntity model, PageRequest page);

}
