package by.gurinovich.carseller.carsellerbot.utils.parsers;

import org.springframework.stereotype.Component;

@Component
public class CallbackDataParser {

    public Long parseId(String callbackData){
        return Long.parseLong(callbackData.substring(callbackData.lastIndexOf("_")+1));
    }

}
