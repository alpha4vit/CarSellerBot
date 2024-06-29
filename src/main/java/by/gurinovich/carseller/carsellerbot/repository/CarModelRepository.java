package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModelEntity, Long> {
    List<CarModelEntity> findAllByBrandOrderByName(CarBrandEntity brand, PageRequest page);

    @Query(nativeQuery = true, value = "SELECT CEIL(COUNT(*) / CAST(:page_size AS DOUBLE PRECISION)) AS totalPages FROM car_models where car_brand_id = :brand_id;")
    Long findPagesCount(@Param("page_size") Integer pageSize, @Param("brand_id") Long brand_id);

}
