package com.training.petfood;

import org.springframework.integration.annotation.Aggregator;

import java.util.List;
import java.util.stream.Collectors;

public class StringAggregator {

    @Aggregator
    public String aggregate(List<String> messages) {
        return messages.stream()
                .collect(Collectors.joining("-"));
    }
}
