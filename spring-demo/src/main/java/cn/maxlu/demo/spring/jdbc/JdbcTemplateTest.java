package cn.maxlu.demo.spring.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luwei on 2017/7/11.
 */
public class JdbcTemplateTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/jdbc/spring-context-jdbc.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

        //执行sql语句
        jdbcTemplate.execute("TRUNCATE TABLE luweitest.user;");

        //插入
        int count = jdbcTemplate.update("INSERT INTO user(name) VALUES (?)", "max");
        System.out.println(count);

        //删除
        int count2 = jdbcTemplate.update("DELETE FROM user WHERE name = ?", "max");
        System.out.println(count2);

        //批量插入
        List<Object[]> values = new ArrayList<>();
        values.add(new Object[]{"max1"});
        values.add(new Object[]{"max2"});
        values.add(new Object[]{"max3"});
        int[] count3 = jdbcTemplate.batchUpdate("INSERT INTO user(name) VALUES (?)", values);
        System.out.println(Arrays.toString(count3));

        //数据映射
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE name = ?", rowMapper, "max1");
        System.out.println(user);

        //list数据
        List<User> users = jdbcTemplate.query("SELECT * FROM user", rowMapper);
        for (User d : users) {
            System.out.println(d);
        }

        //查询单值（sum、count、max等函数）
        Integer count4 = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", Integer.class);
        System.out.println(count4);

    }
}
