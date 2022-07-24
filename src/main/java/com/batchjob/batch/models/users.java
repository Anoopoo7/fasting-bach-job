package com.batchjob.batch.models;

import java.util.Date;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "fas_users")
public class users {
    private String id;
    private String first_name;
    private String last_name;
    private int age;
    private String gender;
    private String bio;
    private String image;
    private String email;
    private String password;
    private Map<String, Boolean> fastIds;
    private boolean active;
    private Date lastUpdated;
}
