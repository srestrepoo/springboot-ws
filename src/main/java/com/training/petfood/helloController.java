package com.training.petfood;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("/hello")
    public String getHelloMessage(){
        return "Hello World!";
    }
}
