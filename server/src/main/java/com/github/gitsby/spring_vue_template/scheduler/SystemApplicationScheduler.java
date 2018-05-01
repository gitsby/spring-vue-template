package com.github.gitsby.spring_vue_template.scheduler;

import com.github.gitsby.spring_vue_template.config.ConfigFactory;
import com.github.gitsby.spring_vue_template.config.EmailConfig;
import kz.greetgo.email.EmailSenderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemApplicationScheduler {

    @Autowired
    private EmailSenderController emailSenderController;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private ConfigFactory configFactory;


    @Scheduled(fixedRate = 5000)
    public void sendEmails() {
        emailSenderController.sendAllExistingEmails();
    }


    @Scheduled(cron = "0 0 6 * * *")
    public void cleanOldEmails() {
        emailSenderController.cleanOldSentFiles(emailConfig.keepDays());
    }


    @Scheduled(cron = "0/30 * * * * ?")
    public void resetHotConfigs() {
        configFactory.reset();
    }


}
