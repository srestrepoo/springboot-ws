package com.training.petfood;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringFilterHandler implements GenericHandler<List<String>> {

    @Override
    public Message<List<String>> handle(List<String> payload, MessageHeaders headers) {
        List<String> filteredList = payload.stream()
                .filter(word -> word.substring(0,1).toUpperCase().compareTo("C") != 0)
                .filter(word -> word.substring(0,1).toUpperCase().compareTo("E") != 0)
                .filter(word -> word.substring(0,1).toUpperCase().compareTo("S") != 0)
                .filter(word -> word.substring(0,1).toUpperCase().compareTo("A") != 0)
                .filter(word -> word.substring(0,1).toUpperCase().compareTo("R") != 0)
                .collect(Collectors.toList());
        return MessageBuilder.withPayload(filteredList).build();
    }
}
