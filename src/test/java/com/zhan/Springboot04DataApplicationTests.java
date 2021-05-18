package com.zhan;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhan.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate template;

    @Test
    void contextLoads() throws SQLException {

        System.out.println(dataSource.getClass());//class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getConnection());//HikariProxyConnection@64364705 wrapping com.mysql.cj.jdbc.ConnectionImpl@4f486211

    }

    @Test
    void selectStudentTest(){
        String sql = "select * from mybatis_user.student";
        List<Student> students = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        System.out.println(students);
        //[Student(id=1, tid=1, name=张三),
        // Student(id=2, tid=1, name=李四),
        // Student(id=3, tid=1, name=王五),
        // Student(id=4, tid=1, name=李六),
        // Student(id=5, tid=1, name=田七)]

        DruidDataSource ds= (DruidDataSource)dataSource;
        System.out.println("最大连接数"+ds.getMaxActive());
    }

}
