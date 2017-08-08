package cn.maxlu.demo.mybatis.db;

import cn.maxlu.demo.mybatis.dao.DemoAnnotationMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by luwei on 2017/6/30.
 */

public class SqlSessionFactoryDemo {

    private static final String DEFAULT_MYBATIS_CONFIG_FILE = "mybatis-config.xml";

    public static SqlSessionFactory getSqlSessionFactoryByXml(String configFile) {
        if (configFile == null || configFile.trim().length() == 0) {
            configFile = DEFAULT_MYBATIS_CONFIG_FILE;
        }
        try (InputStream inputStream = Resources.getResourceAsStream(configFile)) {
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }

    public static SqlSessionFactory getSqlSessionFactoryByJava() {
        DataSource dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://192.168.0.101:3306/luweitest", "root", "123456");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        //要求mapper类中方法使用注解的方式指明sql
        configuration.addMapper(DemoAnnotationMapper.class);

        return new SqlSessionFactoryBuilder().build(configuration);
    }


    public static SqlSession getSession(boolean useXml) {
        if (useXml) {
            return getSqlSessionFactoryByXml("mybatis-config.xml").openSession(true);
        } else {
            return getSqlSessionFactoryByJava().openSession(true);
        }
    }

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryByXml("mybatis-config.xml");
        System.out.println(sqlSessionFactory);

        SqlSessionFactory sqlSessionFactory2 = getSqlSessionFactoryByJava();
        System.out.println(sqlSessionFactory2);
    }
}
