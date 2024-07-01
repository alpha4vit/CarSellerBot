package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.CarEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelGenerationEntity;
import by.gurinovich.carseller.carsellerbot.props.BotProperties;
import by.gurinovich.carseller.carsellerbot.repository.CarRepository;
import by.gurinovich.carseller.carsellerbot.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final BotProperties botProperties;

    @Override
    public List<CarEntity> getPageByGeneration(Long page, CarModelGenerationEntity generation) {
        return carRepository.findAllByGeneration(
                PageRequest.of(page.intValue(), botProperties.getCarLotPageSize()),
                generation);
    }

    @Override
    public Long getPagesCountByGenerationId(Long generationId) {
        return carRepository.findPagesCount(
                botProperties.getCarLotPageSize(),
                generationId
        );
    }
}
