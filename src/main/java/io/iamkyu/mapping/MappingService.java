package io.iamkyu.mapping;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MappingService {

    private final MappingMongoRepository mongoRepository;

    public MappingService(MappingMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public void mapping(String key, String url) {
        KeyMapper keyMapper = new KeyMapper(key, url, LocalDateTime.now());
        mongoRepository.save(keyMapper);
    }

    public KeyMapper get(String key) {
        return mongoRepository.findById(key)
                .orElseThrow(RuntimeException::new);
    }
}
