package com.batchjob.batch.models;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmailRequest {
    private String eventId;
    private String to;
    private Map<String, String> KeysWords;
}
