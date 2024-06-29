package by.gurinovich.carseller.carsellerbot.utils.parsers;

import org.springframework.stereotype.Component;

@Component
public class CallbackDataParser {

    public Long parseId(String callbackData){
        return Long.parseLong(callbackData.substring(callbackData.lastIndexOf("_")+1));
    }

    public Long parseBrandIdFromModelMessage(String message){
        return Long.parseLong(message.substring(
                message.indexOf(" ")+1, message.indexOf(".")
        ));
    }

}
