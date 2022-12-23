package com.example.visionarycrofting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class VisionaryCroftingApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisionaryCroftingApplication.class, args);
    }

}
