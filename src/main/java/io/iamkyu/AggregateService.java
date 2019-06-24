package io.iamkyu;

import io.iamkyu.generate.KeyGenerateService;
import io.iamkyu.mapping.MappingService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    public ShortUrlResponse mapping(String url) {
        String key = keyGenerateService.getKey();
        String mappedUrl = mappingService.mapping(key, url);
        return ShortUrlResponse.of(mappedUrl);
    }

    public String getActualUrl(String key) {
        return mappingService.getUrl(key);
    }


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class ShortUrlResponse {
        private String shortUrl;
    }

}
