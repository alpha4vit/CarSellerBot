package by.gurinovich.carseller.carsellerbot.entity;

import by.gurinovich.carseller.carsellerbot.utils.enums.ReviewType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private String videoId;

    @Enumerated
    @Column(name = "type")
    private ReviewType reviewType;

}
