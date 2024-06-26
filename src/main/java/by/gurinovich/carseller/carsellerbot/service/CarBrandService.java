package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;

import java.util.List;

public interface CarBrandService {

    Long getPagesCount();

    List<CarBrandEntity> getAllOrderedByName(Long page);

    CarBrandEntity getById(Long id);
}
