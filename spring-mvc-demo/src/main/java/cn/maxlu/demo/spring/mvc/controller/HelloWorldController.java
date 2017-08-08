package cn.maxlu.demo.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("msg", "hello world");
		return "index";
	}
	
	@RequestMapping(value="/json", produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String jsonResponse() {
		return "{\"name\":\"和\"}";
	}

	@RequestMapping(value="/ex")
	public String exceptionTest() {
		throw new RuntimeException("test ex");
	}

	@RequestMapping(value="/param/{name}")
	@ResponseBody
	public String exceptionTest(ModelMap model, @PathVariable("name") String name) {
		return "{\"msg\":\"OK\"}";
	}

    @RequestMapping("/cookie")
    @ResponseBody
    public String cookieTest(HttpServletRequest request) {
        String contextPath = request.getContextPath();
//        System.out.println(contextPath);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
//            System.out.println(cookie.getName() + "=" + cookie.getValue());
        }
        return "{\"msg\":\"OK\"}";
    }
}
