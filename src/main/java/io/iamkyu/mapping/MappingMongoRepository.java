package io.iamkyu.mapping;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface MappingMongoRepository extends MongoRepository<KeyMapper, String> {
}
