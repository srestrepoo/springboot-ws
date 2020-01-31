package com.training.petfood;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.List;

@MessagingGateway(name = "StringTransformationGateway")
public interface StringTransformationGateway {

    @Gateway(requestChannel = "StringChannel")
    String stringTransformation(List<String> wordList);

}
