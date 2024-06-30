package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.CarModelEntity;
import by.gurinovich.carseller.carsellerbot.entity.CarModelGenerationEntity;
import by.gurinovich.carseller.carsellerbot.props.BotProperties;
import by.gurinovich.carseller.carsellerbot.repository.CarModelGenerationRepository;
import by.gurinovich.carseller.carsellerbot.service.CarModelGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarModelGenerationServiceImpl implements CarModelGenerationService {

    private final CarModelGenerationRepository carModelGenerationRepository;
    private final BotProperties botProperties;

    @Override
    public List<CarModelGenerationEntity> getByModelOrderedByName(Long page, CarModelEntity model) {
        return carModelGenerationRepository.findAllByCarModelEntity(
                model,
                PageRequest.of(page.intValue(), botProperties.getCarPageSize()));
    }

    @Override
    public Long getPagesCountByBrandId(Long modelId) {
        return carModelGenerationRepository.findPagesCount(
                botProperties.getCarPageSize(),
                modelId
        );
    }
}
