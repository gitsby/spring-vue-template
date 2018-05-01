package com.github.gitsby.spring_vue_template._develop_;

import com.github.gitsby.spring_vue_template.liquibase.PostgresLiquibase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Kasyanov Maxim on 8/24/2017.
 */
public class LiquibaseRecreate {

    private final Log log = LogFactory.getLog(getClass());

    public static void main(String[] args) throws Exception {
        new LiquibaseRecreate().run();
    }

    /**
     * Метод для наката стуркутеры базы
     * ИСПОЛЬЗОВАТЬ ТОЛЬКО ДЛЯ ЛОКАЛЬНОЙ БАЗЫ!!!
     * Удаляет бд и накатывает все change-log-и.
     * Для того что запустить, нужно создать в локальной бд базу geoservice(из database.properties свойсвтво database)
     * После создания базы, нужно запусить данный класс
     * @throws Exception
     */
     void run() throws Exception {
        DbWorker w = new DbWorker();

        log.info("OK. Очистка БД...");
        w.recreate();
        log.info("OK. Накат liquibase...");
        new PostgresLiquibase().apply(false);

        log.info("Готово.");
    }
}
