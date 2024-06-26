package by.gurinovich.carseller.carsellerbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car_model_generations")
public class CarModelGenerationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "generation_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "car_model_id", referencedColumnName = "id")
    private CarModelEntity carModelEntity;

    @Column(name = "start_year_of_prod")
    private Date startYearOfProduction;

    @Column(name = "last_year_of_prod")
    private Date endYearOfProduction;

}

