package com.github.gitsby.spring_vue_template.service.impl;

import com.github.gitsby.spring_vue_template.service.MailService;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSaver;


public class MailServiceImpl implements MailService {

    private EmailSaver emailSaver;

    public MailServiceImpl(EmailSaver emailSaver) {
        this.emailSaver = emailSaver;
    }

    @Override
    public void send(Email email) {
        emailSaver.send(email);
    }

    @Override
    public void send(String subject, String content, String from, String to) {
        Email email = new Email();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setBody(content);
        send(email);
    }
}
