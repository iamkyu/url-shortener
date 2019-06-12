package io.iamkyu;

import io.iamkyu.kgs.KeyGenerateService;
import io.iamkyu.kgs.KeyJdbcRepository;
import io.iamkyu.kgs.KeyRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

    @Autowired
    KeyJdbcRepository repository;

    @Autowired
    KeyRedisRepository keyRedisRepository;

    @Autowired
    KeyGenerateService service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // TODO will be removed
    @Bean
    public CommandLineRunner runner() {
        return (a) -> {
            String key = service.getKey();
            log.info("key: {}", key);
        };
    }
}
