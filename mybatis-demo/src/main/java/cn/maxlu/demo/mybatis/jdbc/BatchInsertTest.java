package cn.maxlu.demo.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Created by luwei on 2017/7/3.
 */
public class BatchInsertTest {
    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.101:3306/luweitest", "root", "123456");
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.addBatch("INSERT INTO demo(name) VALUES ('max')");
        statement.addBatch("INSERT INTO demo(name) VALUES ('max')");
        statement.addBatch("INSERT INTO demo(name) VALUES ('max')");
        statement.addBatch("INSERT INTO demo(name) VALUES ('max')");
        statement.addBatch("INSERT INTO demo(name) VALUES ('max')");
        int[] ints = statement.executeBatch();
        System.out.println(Arrays.toString(ints));
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
