package com.github.gitsby.spring_vue_template.dao;

import com.github.gitsby.spring_vue_template.model.Test1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Test {

    @Select("select * from test")
    List<Test1> test();


    @Insert("insert into test values(1)")
    void insert();
}
