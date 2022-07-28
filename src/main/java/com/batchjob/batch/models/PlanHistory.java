package com.batchjob.batch.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "fas_plan_history")
public class PlanHistory {
    @Id
    private String id;
    private String userId;
    private String fastName;
    private String fastId;
    private boolean complete;
    private Map<String, List<Fasting_item>> activity;
    private boolean success;
    private Date startDate;
    private Date updatedDate;

}
