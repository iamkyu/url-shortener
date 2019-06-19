package io.iamkyu;

import io.iamkyu.generate.KeyGenerateService;
import io.iamkyu.mapping.MappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AggregateService {
    private final KeyGenerateService keyGenerateService;
    private final MappingService mappingService;

    public AggregateService(KeyGenerateService keyGenerateService, MappingService mappingService) {
        this.keyGenerateService = keyGenerateService;
        this.mappingService = mappingService;
    }

    @Transactional
    public void mapping(String url) {
        String key = keyGenerateService.getKey();
        mappingService.mapping(key, url);
    }
}
