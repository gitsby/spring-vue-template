package com.github.gitsby.spring_vue_template.liquibase;

import com.github.gitsby.spring_vue_template.configurations.TemplateConfig;
import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Kasyanov Maxim on 8/16/2017.
 */
public class PostgresLiquibase {
    private final Log log = LogFactory.getLog(getClass());

    public void apply(boolean needCheckUpgrade) throws Exception {

        Params params = Params.readParams();
        if (needCheckUpgrade) {
            File checkFile = new File(TemplateConfig.appDir() + "/do.not.upgrade.db");
            if (checkFile.exists()) {
                log.info("Запуск liquibasе отменён, потому что есть файл: " + checkFile);
                return;
            }
        }
        Class.forName(params.driver);
        try (Connection connection = DriverManager.getConnection(params.url(), params.username, params.password)) {
            PostgresDatabase postgresDatabase = new PostgresDatabase();
            postgresDatabase.setConnection(new JdbcConnection(connection));
            new Liquibase("liquibase/changelog-master.xml", new ClassLoaderResourceAccessor(), postgresDatabase).update("");
        }
    }
}
