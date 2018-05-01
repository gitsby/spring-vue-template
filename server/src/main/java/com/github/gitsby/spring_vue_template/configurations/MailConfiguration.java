package com.github.gitsby.spring_vue_template.configurations;

import com.github.gitsby.spring_vue_template.config.EmailConfig;
import com.github.gitsby.spring_vue_template.service.MailService;
import com.github.gitsby.spring_vue_template.service.impl.EmailSenderImpl;
import com.github.gitsby.spring_vue_template.service.impl.MailServiceImpl;
import kz.greetgo.email.EmailSaver;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;
import kz.greetgo.email.from_spring.javamail.JavaMailSender;
import kz.greetgo.email.from_spring.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Autowired
    private EmailConfig emailConfig;

    private EmailSaver emailSaver() {
        return new EmailSaver("Geoservice-", TemplateConfig.emailDir() + "/to_send");
    }

    @Bean
    public EmailSenderController emailSenderController() {
        System.out.println(2);
        return new EmailSenderController(emailSender(), new File(TemplateConfig.emailDir() + "/to_send"), new File(TemplateConfig.emailDir() + "/sent"));
    }


    @Bean
    public MailService mailService() {
        return new MailServiceImpl(emailSaver());
    }

    private EmailSender emailSender() {
        return new EmailSenderImpl(mailSender());
    }

    private JavaMailSender mailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setPort(25);
        javaMailSender.setUsername(emailConfig.username());
        javaMailSender.setPassword(emailConfig.password());
        javaMailSender.setHost("smtp.mail.ru");
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }


    private Properties getMailProperties() {

        Properties properties = new Properties();

        properties.setProperty("mail.smtp.auth", "false");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.mail.ru");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        return properties;
    }
}
