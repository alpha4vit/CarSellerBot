package by.gurinovich.carseller.carsellerbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private CarBrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private CarModelEntity model;

    @ManyToOne
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    private CarModelGenerationEntity generation;

    private String vin;

    private String hp;

    @Column(name = "engine_vol")
    private String engineVol;

    private Integer mileage;

    private Integer price;

}
