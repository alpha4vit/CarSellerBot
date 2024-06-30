package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelGenerationEntity;

import java.util.List;

public interface CarModelGenerationService {
    List<CarModelGenerationEntity> getByModelOrderedByName(Long page, CarModelEntity model);

    Long getPagesCountByBrandId(Long modelId);
}
