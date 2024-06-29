package by.gurinovich.carseller.carsellerbot.repository;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrandEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT CEIL(COUNT(*) / CAST(:page_size AS DOUBLE PRECISION)) AS totalPages FROM car_brands;")
    Long findPagesCount(@Param("page_size") Integer pageSize);
}
