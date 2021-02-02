package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

    private static final Logger LOGGER = LogManager.getLogger(CustomController.class.getName());

    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public String custom() {
        LOGGER.info("Accessing /custom endpoint");
        return "custom";
    }
}