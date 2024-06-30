package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelGenerationEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelGenerationRepository extends JpaRepository<CarModelGenerationEntity, Long> {

    List<CarModelGenerationEntity> findAllByCarModelEntity(CarModelEntity model, PageRequest page);

    @Query(nativeQuery = true, value = "SELECT CEIL(COUNT(*) / CAST(:page_size AS DOUBLE PRECISION)) AS totalPages FROM car_model_generations where car_model_id = :model_id;")
    Long findPagesCount(@Param("page_size") Integer pageSize, @Param("model_id") Long modelId);

}
