package com.app.onlinequizapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OnlineQuizApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineQuizApiApplication.class, args);
    }

}

@RestController
@RequestMapping("/")
class WelcomeController {
    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to the Online Quiz API!");
    }

}