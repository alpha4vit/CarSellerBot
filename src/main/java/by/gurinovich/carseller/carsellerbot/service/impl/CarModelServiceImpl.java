package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;
import by.gurinovich.carseller.carsellerbot.props.BotProperties;
import by.gurinovich.carseller.carsellerbot.repository.CarModelRepository;
import by.gurinovich.carseller.carsellerbot.service.CarModelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;
    private final BotProperties botProperties;

    @Override
    public List<CarModelEntity> getAllOrderedByName() {
        return carModelRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public CarModelEntity getById(Long id) {
        return carModelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car model with this id not found!"));
    }

    @Override
    public List<CarModelEntity> getByBrandOrderedByName(Long page, CarBrandEntity brand) {
        return carModelRepository.findAllByBrandOrderByName(brand, PageRequest.of(page.intValue(), 3));
    }

    @Override
    public Long getPagesCountByBrandId(Long brandId) {
        return carModelRepository.findPagesCount(botProperties.getCarPageSize(), brandId);
    }

}
