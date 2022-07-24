package com.batchjob.batch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.batchjob.batch.models.users;

@Repository
public interface UserRepository extends MongoRepository<users,String> {
    
}
