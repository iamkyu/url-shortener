package io.iamkyu;

import io.iamkyu.generate.KeyGenerateService;
import io.iamkyu.generate.KeyJdbcRepository;
import io.iamkyu.generate.KeyRedisRepository;
import io.iamkyu.mapping.MappingService;
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

    @Autowired
    MappingService mappingService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // TODO will be removed
    @Bean
    public CommandLineRunner runner() {
        return (a) -> {
            String key = service.getKey();
            log.info("key: {}", key);

            mappingService.mapping(key, "foo.bar.com");
            log.info("found: {}", mappingService.get(key));
        };
    }
}
