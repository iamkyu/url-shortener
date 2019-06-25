package io.iamkyu.mapping;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MappingMongoRepository extends MongoRepository<KeyMapper, String> {
    Optional<KeyMapper> findByKey(String key);
}
