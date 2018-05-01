package com.github.gitsby.spring_vue_template.config;

import com.github.gitsby.spring_vue_template.configurations.TemplateConfig;
import kz.greetgo.conf.hot.HotConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigFactory extends HotConfigFactory {

    @Override
    protected String getBaseDir() {
        return TemplateConfig.configDir();
    }

    @Bean
    public EmailConfig emailConfig() {
        return createConfig(EmailConfig.class);
    }
}
