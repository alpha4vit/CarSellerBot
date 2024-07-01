package by.gurinovich.carseller.carsellerbot.entity;


import by.gurinovich.carseller.carsellerbot.utils.enums.states.BotState;
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
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "username")
    String username;

    @Column(name = "name")
    private String name;

    @Column(name = "bot_state")
    @Enumerated(value = EnumType.ORDINAL)
    private BotState botState;

    @Column(name = "review_num")
    private Long reviewNum;

    @Column(name = "brand_page_num")
    private Long brandPageNum;

    @Column(name = "model_page_num")
    private Long modelPageNum;

    @Column(name = "generation_page_num")
    private Long generationPageNum;

    @Column(name = "car_page")
    private Long carPage;

}
