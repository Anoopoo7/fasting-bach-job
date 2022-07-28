package com.batchjob.batch.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.batchjob.batch.models.FastingPlanProgress;


@Repository
public interface PlanRepository  extends MongoRepository<FastingPlanProgress, String>{

    List<FastingPlanProgress> findAllByStatus(boolean b);

    List<FastingPlanProgress> findAllByStatusAndEnabledAndActiveDaysIn(boolean b, boolean c, List<String> today_List);
    
}
