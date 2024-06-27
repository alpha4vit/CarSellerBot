package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;

import java.util.List;

public interface CarBrandService {
    List<CarBrandEntity> getAllOrderedByName();

    CarBrandEntity getById(Long id);
}
