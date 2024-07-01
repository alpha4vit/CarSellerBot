package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.CarEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelGenerationEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findAllByGeneration(PageRequest page, CarModelGenerationEntity generation);

    @Query(nativeQuery = true, value = "SELECT CEIL(COUNT(*) / CAST(:page_size AS DOUBLE PRECISION)) AS totalPages FROM cars where generation_id = :generation_id;")
    Long findPagesCount(@Param("page_size") Integer pageSize, @Param("generation_id") Long generation_id);
}
