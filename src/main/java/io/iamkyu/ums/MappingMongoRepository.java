package io.iamkyu.ums;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface MappingMongoRepository extends MongoRepository<KeyMapper, String> {
}
