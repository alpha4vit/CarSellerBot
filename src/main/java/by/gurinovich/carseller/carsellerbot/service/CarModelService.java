package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;

import java.util.List;

public interface CarModelService {
    List<CarModelEntity> getAllOrderedByName();

    List<CarModelEntity> getByBrandOrderedByName(CarBrandEntity brand);
}
