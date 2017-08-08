package cn.maxlu.demo.mybatis.domain;

import org.apache.ibatis.type.Alias;

/**
 * Created by luwei on 2017/6/30.
 */

@Alias("demo")
public class Demo {

    public Demo(){}

    public Demo(String name) {
        this.name = name;
    }

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
