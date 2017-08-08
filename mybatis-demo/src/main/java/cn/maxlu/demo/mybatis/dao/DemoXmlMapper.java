package cn.maxlu.demo.mybatis.dao;

import cn.maxlu.demo.mybatis.domain.Demo;

import java.util.List;
import java.util.Map;

/**
 * Created by luwei on 2017/6/30.
 */
public interface DemoXmlMapper {
    Demo selectDemo(int id);

    Demo selectDemo2(int id);

    int insertDemo(Demo demo);

    int batchInsertDemo(List<Demo> list);

    Map<String, Object> returnTypeTest();

    Map<String, Object> returnTypeTest2();

    Map<String, Object> returnTypeTest3();
}
