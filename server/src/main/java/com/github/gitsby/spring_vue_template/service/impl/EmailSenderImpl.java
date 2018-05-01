package com.github.gitsby.spring_vue_template.service.impl;

import com.github.gitsby.spring_vue_template.utils.StringUtils;
import kz.greetgo.email.Attachment;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.from_spring.javamail.JavaMailSender;
import kz.greetgo.email.from_spring.javamail.MimeMessageHelper;

import javax.mail.internet.MimeUtility;
import java.io.ByteArrayInputStream;


public class EmailSenderImpl implements EmailSender {

    private JavaMailSender javaMailSender;


    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(Email email) {
        if (!StringUtils.isNullOrEmpty(email.getTo()) || !StringUtils.isNullOrEmpty(email.getFrom())) {
                send(javaMailSender, email);
        }
    }

    private void send(JavaMailSender javaMailSender, Email email) {
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(email.getFrom());
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getBody(), true);

            for (final Attachment attachment : email.getAttachments()) {
                message.addAttachment(MimeUtility.encodeText(attachment.name), () -> new ByteArrayInputStream(attachment.data));
            }
        });
    }
}
