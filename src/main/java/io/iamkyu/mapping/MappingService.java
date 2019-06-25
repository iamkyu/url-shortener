package io.iamkyu.mapping;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MappingService {

    private final MappingMongoRepository mongoRepository;

    public MappingService(MappingMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public String mapping(String key, String url) {
        KeyMapper keyMapper = new KeyMapper(key, url, LocalDateTime.now());
        KeyMapper saved = mongoRepository.save(keyMapper);
        return saved.getKey();
    }

    public String getUrl(String key) {
        return mongoRepository.findByKey(key)
                .map(KeyMapper::getUrl)
                .orElseThrow(RuntimeException::new);
    }
}
