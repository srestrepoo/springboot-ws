package com.training.petfood;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class StringTransformerHandler implements GenericHandler<String> {

    @Override
    public String handle(String payload, MessageHeaders headers) {
        String transformedString = payload;
        if(startWithVocal(payload)){
            return transformedString.concat(String.valueOf(new Random().nextInt()));
        } else {
            return transformedString.toUpperCase();
        }
    }

    public boolean startWithVocal(String word) {
        return  word.substring(0, 1).toUpperCase().contains("A") ||
                word.substring(0, 1).toUpperCase().contains("E") ||
                word.substring(0, 1).toUpperCase().contains("I") ||
                word.substring(0, 1).toUpperCase().contains("O") ||
                word.substring(0, 1).toUpperCase().contains("U");
    }
}
