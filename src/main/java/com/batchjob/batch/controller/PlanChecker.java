package com.batchjob.batch.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batchjob.batch.models.EmailRequest;
import com.batchjob.batch.services.Planservices;

@Component
// @RestController
public class PlanChecker {

    @Autowired
    private Planservices planservices;

    @Scheduled(cron = "0 */5 * ? * *")
    // @GetMapping("/start")
    public void checkValidPlans() {
        planservices.checkValidPlans();
        System.out.println("......................mail checking @" + new Date() + "....................!");
    }

    @Scheduled(cron = "0 0 0 ? * * ")
    // @GetMapping("/start")
    public void populatePlanResults() {
        planservices.populatePlanResults();
        System.out.println("......................populating plan results @" + new Date() + "....................!");
    }
}
