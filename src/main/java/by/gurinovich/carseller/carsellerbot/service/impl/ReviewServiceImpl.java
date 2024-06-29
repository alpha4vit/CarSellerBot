package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.ReviewEntity;
import by.gurinovich.carseller.carsellerbot.props.BotProperties;
import by.gurinovich.carseller.carsellerbot.repository.ReviewRepository;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BotProperties botProperties;

    @Override
    public ReviewEntity getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found!"));
    }

    @Override
    public ReviewEntity save(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<ReviewEntity> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Page<ReviewEntity> getByPage(long pageNumber) {
        return reviewRepository.findAll(PageRequest.of((int) pageNumber, botProperties.getCarPageSize()));
    }

    @Override
    public Long getAllCount() {
        return reviewRepository.findCount();
    }
}
