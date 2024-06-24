package by.gurinovich.carseller.carsellerbot.service;

import by.gurinovich.carseller.carsellerbot.entity.ReviewEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {

    ReviewEntity getById(Long id);

    ReviewEntity save(ReviewEntity review);

    List<ReviewEntity> getAll();

    Page<ReviewEntity> getByPage(long pageNumber);

    Long getAllCount();

}
