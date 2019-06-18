package io.iamkyu.generate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KeyGenerateService {
    private final int keyCacheSize;
    private final KeyJdbcRepository jdbcRepository;
    private final KeyRedisRepository redisRepository;

    public KeyGenerateService(@Value("${key-service.key-cache-size}") int keyCacheSize,
                              KeyJdbcRepository jdbcRepository,
                              KeyRedisRepository redisRepository) {
        this.keyCacheSize = keyCacheSize;
        this.jdbcRepository = jdbcRepository;
        this.redisRepository = redisRepository;
    }

    @Transactional
    public String getKey() {
        KeyHolder keyHolder = redisRepository.pop()
                .orElseGet(this::dumpUnUsedKeysToRedis);
        return keyHolder.getKey();
    }

    private KeyHolder dumpUnUsedKeysToRedis() {
        List<KeyHolder> keyHolders = jdbcRepository.selectUnusedKeys(keyCacheSize);
        if (keyHolders.isEmpty()) {
            throw new RuntimeException();
        }

        redisRepository.saveAll(keyHolders);
        jdbcRepository.saveUsedKeys(keyHolders);

        return redisRepository.pop()
                .orElseThrow(RuntimeException::new);
    }
}
