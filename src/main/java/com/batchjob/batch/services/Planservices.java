package com.batchjob.batch.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.batchjob.batch.models.EmailModel;
import com.batchjob.batch.models.EmailRequest;
import com.batchjob.batch.models.FastingPlanProgress;
import com.batchjob.batch.models.Fasting_item;
import com.batchjob.batch.models.users;
import com.batchjob.batch.repository.PlanRepository;
import com.batchjob.batch.repository.UserRepository;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class Planservices {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;

    public EmailRequest checkValidPlans() {
        List<FastingPlanProgress> fastingPlanProgress = planRepository.findAllByStatus(false);
        if (null == fastingPlanProgress) {
            return null;
        }
        List<EmailModel> emailModels = new ArrayList<EmailModel>();
        for (FastingPlanProgress plan : fastingPlanProgress) {
            List<Fasting_item> fasting_item = plan.getFastingPlan() != null ? plan.getFastingPlan().getFasting_items()
                    : null;
            for (Fasting_item item : fasting_item) {
                LocalTime itemTime = LocalTime.parse(item.getTime()).minus(330, ChronoUnit.MINUTES);
                LocalTime currentTime = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
                System.out.println(itemTime + "c" + currentTime);
                long timeDifference = MINUTES.between(currentTime, itemTime);
                if (0 < timeDifference && 10 > timeDifference && item.getStatus().equals("PENDING")) {
                    EmailModel emailModel = new EmailModel();
                    users user = userRepository.findById(plan.getUserId()).get();
                    emailModel.setUserId(user.getEmail());
                    emailModel.setUserName(user.getFirst_name());
                    emailModel.setFasting_item(item);
                    emailModel.setFastingName(plan.getFastingPlan().getName());
                    emailModel.setTime(item.getTime());
                    emailModels.add(emailModel);
                    break;
                }
            }
        }
        for (EmailModel emailModel : emailModels) {
            return this.sendMail(emailModel);
        }
        return null;
    }

    private EmailRequest sendMail(EmailModel emailModel) {
        String url = "https://web-backend-app.herokuapp.com/send";
        RestTemplate restTemplate = new RestTemplate();

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEventId("FASTING_EVENT_BATCH_JOB");
        emailRequest.setTo(emailModel.getUserId());
        Map<String, String> KeysWords = new HashMap<String, String>();
        KeysWords.put("#001", emailModel.getUserName());
        KeysWords.put("#002", emailModel.getTime());
        KeysWords.put("#003", emailModel.getFasting_item().getData());
        emailRequest.setKeysWords(KeysWords);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<EmailRequest> entity = new HttpEntity<>(emailRequest, headers);



        System.out.println("sending mail:............:.......:.......:>>>");
        restTemplate.postForObject(url, entity, Object.class);
        return emailRequest;
    }

}
