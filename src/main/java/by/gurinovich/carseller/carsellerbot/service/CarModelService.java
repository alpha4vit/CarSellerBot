package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;

import java.util.List;

public interface CarModelService {
    List<CarModelEntity> getAllOrderedByName();

    CarModelEntity getById(Long id);

    List<CarModelEntity> getByBrandOrderedByName(Long page, CarBrandEntity brand);

    Long getPagesCountByBrandId(Long brandId);

}
