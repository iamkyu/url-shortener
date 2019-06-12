package io.iamkyu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJdbcTest
public class JdbcSpringIntegrationTest {

    @Test
    void name(@Autowired JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("SELECT 1");
    }
}
