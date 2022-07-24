package com.batchjob.batch.models;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fasting_item {
    private String data;
    private String time;
    private String status;
    private Date lastUpdate;
}
