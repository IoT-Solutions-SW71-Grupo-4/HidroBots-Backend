package org.hidrobots.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.access.SecurityConfig;

@SpringBootApplication
@EnableJpaAuditing
public class HidroBotsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HidroBotsBackendApplication.class, args);
    }

}
