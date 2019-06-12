package io.iamkyu.kgs;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class KeyHolder {

    private Long id;
    private String key;

    @Builder
    public KeyHolder(Long id, String key) {
        this.id = id;
        this.key = key;
    }
}
