package com.batchjob.batch.models;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmailModel {
    private String userName;
    private String userId;
    private Fasting_item fasting_item;
    private String FastingName;
    private String time;
}
