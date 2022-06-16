package com.example.demo.service;

import com.example.demo.service.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        log.info("Email has been sent , Message body : " + message);
    }
}
