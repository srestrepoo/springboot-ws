package com.training.petfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.SubscribableChannel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class StringTransformationConfiguration {

    @Autowired
    public StringFilterHandler stringFilterHandler;

    @Autowired
    public StringTransformerHandler stringTransformerHandler;

    @Bean(name = "StringChannel")
    public SubscribableChannel StringChannel(){
        return MessageChannels.direct().get();
    }

    @Bean(name = "stringTransformationExecutor")
    public Executor stringTransformationExecutor() {
        return Executors.newFixedThreadPool(2);
    }

    @Bean
    public StringAggregator getStringAggregator(){
        return new StringAggregator();
    }

    @Bean
    public IntegrationFlow StringTransformationFlow(){
        return IntegrationFlows
                .from(StringChannel())
                .handle(stringFilterHandler)
                .split()
                .channel(c -> c.executor(stringTransformationExecutor()))
                .handle(stringTransformerHandler)
                .aggregate(a -> a.processor(getStringAggregator()))
                .get();
    }
}
