package io.iamkyu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;

@DataRedisTest
public class RedisSpringIntegrationTest {
    @Test
    void test(@Autowired RedisTemplate redisTemplate) {
        //given
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Map<String, String> toSave = new HashMap<>();
        toSave.put("key", "value");

        //when
        valueOperations.set("map", toSave);

        //then
    }
}
