package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrandEntity, Long> {
}
