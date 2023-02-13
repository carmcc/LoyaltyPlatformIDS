package it.unicam.cs.ids.loyaltyplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@Configuration
//@ComponentScan(basePackages = "it.unicam.cs.ids.loyaltyplatform.service")
//@ComponentScan(basePackages = "it.unicam.cs.ids.loyaltyplatform.controller")
//@ComponentScan(basePackages = "it.unicam.cs.ids.loyaltyplatform.repository")
//@ComponentScan(basePackages = "it.unicam.cs.ids.loyaltyplatform.trigger")
public class LoyaltyPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoyaltyPlatformApplication.class, args);
    }

}
