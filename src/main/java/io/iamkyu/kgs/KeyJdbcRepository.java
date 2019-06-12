package io.iamkyu.kgs;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KeyJdbcRepository {
    private static final String SELECT_UNUSED_KEYS = "SELECT id, key FROM unused_key LIMIT ?";
    private static final String INSERT_USED_KEYS_FORMAT = "INSERT INTO used_key (key) VALUES (?)";

    private final JdbcTemplate jdbcTemplate;

    public KeyJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<KeyHolder> selectUnusedKeys(int limit) {
        return jdbcTemplate.query(SELECT_UNUSED_KEYS, new Object[]{limit},
                (rs, rowNum) -> new KeyHolder(
                        rs.getLong("id"),
                        rs.getString("key")
                ));
    }

    public void saveUsedKeys(List<KeyHolder> entities) {
        jdbcTemplate.batchUpdate(INSERT_USED_KEYS_FORMAT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                KeyHolder keyHolder = entities.get(i);
                ps.setString(1, keyHolder.getKey());
            }

            @Override
            public int getBatchSize() {
                return entities.size();
            }
        });
    }
}
