package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;
import by.gurinovich.carseller.carsellerbot.repository.CarModelRepository;
import by.gurinovich.carseller.carsellerbot.service.CarModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    @Override
    public List<CarModelEntity> getAllOrderedByName() {
        return carModelRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<CarModelEntity> getByBrandOrderedByName(CarBrandEntity brand) {
        return carModelRepository.findAllByBrandOrderByName(brand);
    }

}
