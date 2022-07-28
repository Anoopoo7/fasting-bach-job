package com.batchjob.batch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.batchjob.batch.models.PlanHistory;

@Repository
public interface PlanHistoryRepository extends MongoRepository<PlanHistory, String> {

    PlanHistory findByUserIdAndFastIdAndComplete(String userId, String id, boolean b);

    PlanHistory findByUserId(String userId);

    PlanHistory findByUserIdAndFastId(String userId, String id);


}
