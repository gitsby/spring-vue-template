package com.github.gitsby.spring_vue_template.configurations;

public class TemplateConfig {

    public static String appDir(){
        return System.getProperty("user.home") + "/spring_vue_template.d";
    }

    public static String emailDir(){
        return appDir()+  "/emails";
    }

    public static String configDir(){
        return appDir()+  "/config";
    }

    public static String pathDb(){
        return appDir()+  "/db.properties";
    }

}
