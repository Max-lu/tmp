package cn.maxlu.demo.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by luwei on 2017/7/14.
 */

@Controller
public class ViewTestController {

    @RequestMapping("view1")
    public ModelAndView view1() {
        return new ModelAndView("view1");
    }
}
