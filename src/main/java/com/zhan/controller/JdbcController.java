package com.zhan.controller;


import com.zhan.domain.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.StubDelegate;
import java.util.List;

@RestController
public class JdbcController {

    private JdbcTemplate template;

    @Autowired
    public JdbcController(JdbcTemplate template) {
        this.template = template;
    }

    @GetMapping(value = "/select")
    public List<Student> selectAllStudent(){
        String sql = "select * from mybatis_user.student";
        List<Student> students = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        System.out.println(students);
        return students;
    }

    @GetMapping(value = "/find/{id}")
    public Student find(@PathVariable(value = "id") Integer id){
        String sql  = "select * from mybatis_user.student where id = " + id;
        Student student = null;
        try {
            student = template.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class));
        }catch (Exception e){
            System.out.println("执行失败");
            System.out.println("被追踪过");
        }
        return student;
    }

}
