package cn.maxlu.demo.spring.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luwei on 2017/7/24.
 */
public class Test {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        int a = 1;
        Integer b = 1;
        map.put("a", a);
        map.put("b", b);

        System.out.println(map.get("a").getClass());
        System.out.println(map.get("b").getClass());
    }
}
