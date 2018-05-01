package com.github.gitsby.spring_vue_template._develop_;

import com.github.gitsby.spring_vue_template.configurations.TemplateConfig;
import com.github.gitsby.spring_vue_template.liquibase.Params;
import kz.greetgo.conf.ConfData;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbWorker {
    private Params params = null;


    void recreate() throws Exception {
        init();
        Params params = Params.readParams();

        Params temp = new Params();
        temp.password = params.password;
        temp.username = params.username;
        temp.url = params.url;
        temp.database = "temp_db" + System.currentTimeMillis();

        Connection connection = getConnection(params.username, params.password, params.url());

        createDb(temp, connection);//Создаем временную базу, чтобы удалить базу geoservice
        connection.close();
        Connection connectionTemp = getConnection(params.username, params.password, temp.url());
        try {
            killDb(params, connectionTemp);
            createDb(params, connectionTemp);
            connectionTemp.close();
        } finally {
            connection = getConnection(params.username, params.password, params.url());
            killDb(temp, connection);
            connection.close();
        }

    }

    void killDb(Params params, Connection con) throws Exception {
        try {
            con.prepareStatement("drop database if EXISTS " + params.database)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    void createDb(Params params, Connection con) throws Exception {

        try (PreparedStatement ps = con.prepareStatement("create database " + params.database /*+ " WITH password '" + params.password + "'"*/)) {
            ps.executeUpdate();
        }
        try (PreparedStatement ps = con.prepareStatement("GRANT ALL ON DATABASE " + params.database + " TO " + params.username)) {
            ps.executeUpdate();
        }
    }


    private Connection getConnection(String username, String password, String url) throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    private void createPropertiesFile(File f) throws Exception {
        f.getParentFile().mkdirs();
        PrintStream out = new PrintStream(f, "UTF-8");


        out.println("");
        out.println("db.driver=org.postgresql.Driver");
        out.println("db.url=jdbc:postgresql://localhost/");
        out.println("db.database=template");
        out.println("db.username=postgres");
        out.println("db.password=1");
        out.println("");

        out.close();
    }

    private void init() throws Exception {
        initParams();
    }

    private void initParams() throws Exception {
        if (params != null) return;
        File f = new File(TemplateConfig.pathDb());
        if (!f.exists()) createPropertiesFile(f);
        params = readParams(f);

        new File(f.getParent() + "/do.not.upgrade.db").createNewFile();
    }

    private Params readParams(File f) throws Exception {
        Params ret = new Params();

        ConfData cd = new ConfData();
        cd.readFromFile(f);

        ret.url = cd.str("db.url");
        ret.database = cd.str("db.database");
        ret.username = cd.str("db.username");
        ret.password = cd.str("db.password");

        return ret;
    }

}
