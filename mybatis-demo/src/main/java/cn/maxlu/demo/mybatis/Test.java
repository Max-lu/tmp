package cn.maxlu.demo.mybatis;

import cn.maxlu.demo.mybatis.dao.DemoAnnotationMapper;
import cn.maxlu.demo.mybatis.dao.DemoXmlMapper;
import cn.maxlu.demo.mybatis.db.SqlSessionFactoryDemo;
import cn.maxlu.demo.mybatis.domain.Demo;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by luwei on 2017/6/30.
 */

@SuppressWarnings("unused")
public class Test {
    public static void main(String[] args) {
        SqlSession session = SqlSessionFactoryDemo.getSession(true);
        DemoXmlMapper mapper = session.getMapper(DemoXmlMapper.class);

        Map<String, Object> map = mapper.returnTypeTest();
        Object status = map.get("status");
        System.out.println(status.getClass()); //class java.lang.Long

        Map<String, Object> map2 = mapper.returnTypeTest2();
        Object status2 = map2.get("status");
        System.out.println(status2.getClass()); //class java.lang.Integer

        Map<String, Object> map3 = mapper.returnTypeTest3();
        Object status3 = map3.get("status");
        System.out.println(status3.getClass());
    }

    private static void select2() {
        SqlSession session2 = SqlSessionFactoryDemo.getSession(true);
        DemoXmlMapper mapper2 = session2.getMapper(DemoXmlMapper.class);
        Demo demo2 = mapper2.selectDemo2(2);
        System.out.println(demo2);
    }

    private static void testBatchInsert() {
        SqlSession session = SqlSessionFactoryDemo.getSession(true);
        DemoXmlMapper mapper = session.getMapper(DemoXmlMapper.class);
        List<Demo> demos = new ArrayList<>();
        demos.add(new Demo("max"));
        demos.add(new Demo("max"));
        demos.add(new Demo("max"));
        demos.add(new Demo("max"));
        mapper.batchInsertDemo(demos);
    }

    private static void testInsert() {
        SqlSession session = SqlSessionFactoryDemo.getSession(true);
        DemoXmlMapper mapper = session.getMapper(DemoXmlMapper.class);
        Demo demo = new Demo("max");
        mapper.insertDemo(demo);
        System.out.println(demo.getId());
    }

    private static void testSelect() {
        //注解形式
        SqlSession session = SqlSessionFactoryDemo.getSession(false);
        DemoAnnotationMapper mapper = session.getMapper(DemoAnnotationMapper.class);
        Demo demo = mapper.selectDemo(1);
        System.out.println(demo);

        //xml形式
        SqlSession session2 = SqlSessionFactoryDemo.getSession(true);
        DemoXmlMapper mapper2 = session2.getMapper(DemoXmlMapper.class);
        Demo demo2 = mapper2.selectDemo(1);
        System.out.println(demo2);
    }
}
