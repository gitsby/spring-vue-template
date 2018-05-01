package com.github.gitsby.spring_vue_template.service;

import kz.greetgo.email.Email;

public interface MailService {

    void send(String subject, String content, String from, String to);

    void send(Email email);

}
