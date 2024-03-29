package io.iamkyu.mapping;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Document(collection = "key_mapping")
public class KeyMapper {
    @Id
    private String id;

    @Indexed
    private String key;
    private String url;
    private LocalDateTime createdAt;

    public KeyMapper(String key, String url, LocalDateTime createdAt) {
        this.key = key;
        this.url = url;
        this.createdAt = createdAt;
    }
}
