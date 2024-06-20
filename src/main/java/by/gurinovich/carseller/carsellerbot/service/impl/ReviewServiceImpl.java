package by.gurinovich.carseller.carsellerbot.service.impl;

import by.gurinovich.carseller.carsellerbot.entity.ReviewEntity;
import by.gurinovich.carseller.carsellerbot.repository.ReviewRepository;
import by.gurinovich.carseller.carsellerbot.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewEntity save(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<ReviewEntity> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Long getAllCount() {
        return reviewRepository.findCount();
    }
}
