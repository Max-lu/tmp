package cn.maxlu.demo.spring.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by luwei on 2017/7/4.
 */

@Component
class DemoBean {

    @Autowired
    @Qualifier
    DemoBean2 bean;
}
