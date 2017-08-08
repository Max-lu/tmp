package cn.maxlu.demo.mybatis.dao;

import cn.maxlu.demo.mybatis.domain.Demo;
import org.apache.ibatis.annotations.Select;

/**
 * Created by luwei on 2017/6/30.
 */

public interface DemoAnnotationMapper {
    @Select("select * from demo where id = #{id}")
    Demo selectDemo(int id);
}
