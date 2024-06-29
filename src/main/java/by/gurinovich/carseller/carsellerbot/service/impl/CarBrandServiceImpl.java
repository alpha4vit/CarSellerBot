package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.CarBrandEntity;
import by.gurinovich.carseller.carsellerbot.props.BotProperties;
import by.gurinovich.carseller.carsellerbot.repository.CarBrandRepository;
import by.gurinovich.carseller.carsellerbot.service.CarBrandService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;
    private final BotProperties botProperties;

    @Override
    public Long getPagesCount() {
        return carBrandRepository.findPagesCount(botProperties.getCarPageSize());
    }

    @Override
    public List<CarBrandEntity> getAllOrderedByName(Long page) {
        return carBrandRepository.findAll(
                PageRequest.of(
                        page.intValue(),
                        botProperties.getCarPageSize(),
                        Sort.by(Sort.Direction.ASC, "name")))
                .getContent();
    }

    @Override
    public CarBrandEntity getById(Long id) {
        return carBrandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car brand with this id not found!"));
    }
}
