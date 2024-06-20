package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {

    ReviewEntity save(ReviewEntity review);

    List<ReviewEntity> getAll();

    Long getAllCount();

}
