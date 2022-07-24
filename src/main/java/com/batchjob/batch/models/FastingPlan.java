package com.batchjob.batch.models;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class FastingPlan {
    private String id;
    private String name;
    private String foodType;
    private String ageGroup;
    private Integer duration;
    private List<Fasting_item> fasting_items;
    private List<String> labels;
    private boolean visible;
    private boolean accesseble;
    private Integer deficultyRate;
    private Integer successRate;
    private Integer totalUsers;
    private List<String> category;
    private boolean active;
    private String createdBy;
    private Date createdDate;
}
