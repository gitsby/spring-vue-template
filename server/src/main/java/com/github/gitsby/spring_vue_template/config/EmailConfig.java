package com.github.gitsby.spring_vue_template.config;

import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

public interface EmailConfig {

    @Description("Учетная запись для отправки писем")
    @DefaultStrValue("testEmail@test.com")
    String username();


    @Description("Пароль от учетной записи для отправки писем")
    @DefaultStrValue("testPassword")
    String password();


    @Description("Количество дней для хранения отправленных писем")
    @DefaultIntValue(14)
    int keepDays();
}
