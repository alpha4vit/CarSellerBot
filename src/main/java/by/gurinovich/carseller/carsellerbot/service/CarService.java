package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.CarEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelGenerationEntity;

import java.util.List;

public interface CarService {
    List<CarEntity> getPageByGeneration(Long page, CarModelGenerationEntity generation);

    Long getPagesCountByGenerationId(Long generationId);
}
