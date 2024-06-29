package by.gurinovich.carseller.carsellerbot.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bot")
public class BotProperties {
    private String name;
    private String token;

    @Value(value = "${bot.reviews.page-size}")
    private Integer reviewPageSize;

    @Value(value = "${bot.cars.page-size}")
    private Integer carPageSize;

}
