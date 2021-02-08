package com.example.demo;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    private static final Logger LOGGER = LogManager.getLogger(GreetingController.class.getName());

    @GetMapping("/greetings")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOGGER.info("Greetings request");
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/org/apache/logging/log4j/Logger.class"));
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/org/apache/logging/log4j/FileAppender.class"));
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/log4j2-spring.xml"));
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
