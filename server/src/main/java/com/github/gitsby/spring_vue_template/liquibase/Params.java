package com.github.gitsby.spring_vue_template.liquibase;


import com.github.gitsby.spring_vue_template.configurations.TemplateConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Kasyanov Maxim on 8/26/2017.
 */

public class Params {

    public String driver;
    public String url;
    public String database;
    public String username;
    public String password;

    public String url() {
        return url + database;
    }

    public static Params readParams() throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream(TemplateConfig.pathDb())) {
            properties.load(inputStream);

            Params ret = new Params();
            ret.driver = properties.getProperty("db.driver");
            ret.username = properties.getProperty("db.username");
            ret.username = properties.getProperty("db.username");
            ret.password = properties.getProperty("db.password");
            ret.url = properties.getProperty("db.url");
            ret.database = properties.getProperty("db.database");

            return ret;
        }
    }
}
