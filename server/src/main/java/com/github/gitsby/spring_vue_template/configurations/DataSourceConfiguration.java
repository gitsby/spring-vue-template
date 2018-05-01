package com.github.gitsby.spring_vue_template.configurations;

import com.github.gitsby.spring_vue_template.liquibase.Params;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "dataSource")
    public DataSource dataSource() throws IOException {

        Params params = Params.readParams();
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(params.driver);
        basicDataSource.setUrl(params.url());
        basicDataSource.setUsername(params.username);
        basicDataSource.setPassword(params.password);
        basicDataSource.setPoolPreparedStatements(true);
        basicDataSource.setMaxActive(25);
        basicDataSource.setMaxIdle(15);
        basicDataSource.setInitialSize(5);

        return basicDataSource;
    }
}
