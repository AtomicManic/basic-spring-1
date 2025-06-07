package com.handson.basic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.handson.basic.model.MessageAndPhones;
import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {
    @Value("${sms.ms.url}")
    String SMS_MS_URL;

    protected final Log logger = LogFactory.getLog(getClass());
    OkHttpClient client = new OkHttpClient.Builder().build();

    @Autowired
    RestTemplate rTemplate;

    public String send(MessageAndPhones messageAndPhones) {
        System.out.println(messageAndPhones.getMessage());
        messageAndPhones.getPhones().forEach(
                phone -> System.out.println("Sending SMS to: " + phone)
        );
        return rTemplate.postForObject(SMS_MS_URL + "/api/sms", messageAndPhones, String.class);
    }
}
