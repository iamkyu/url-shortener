package io.iamkyu.kgs;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class KeyRedisRepository {

    private static final String REDIS_KEY = "keys";

    private final RedisTemplate<String, KeyHolder> redisTemplate;

    public KeyRedisRepository(RedisTemplate<String, KeyHolder> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveAll(Collection<KeyHolder> entities) {
        redisTemplate.opsForList().rightPushAll(REDIS_KEY, entities);
    }

    public Optional<KeyHolder> pop() {
        return Optional.ofNullable(redisTemplate.opsForList().leftPop(REDIS_KEY));
    }
}
