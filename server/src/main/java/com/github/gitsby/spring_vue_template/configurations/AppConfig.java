package com.github.gitsby.spring_vue_template.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "com.github.gitsby.spring_vue_template.dao",
        "com.github.gitsby.spring_vue_template.*",
        "com.github.gitsby.spring_vue_template.scheduler",
        "com.github.gitsby.spring_vue_template.config",
})
public class AppConfig {}
