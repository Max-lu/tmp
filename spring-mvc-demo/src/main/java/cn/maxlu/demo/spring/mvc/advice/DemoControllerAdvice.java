package cn.maxlu.demo.spring.mvc.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by dell on 2017/6/23.
 */

@ControllerAdvice
public class DemoControllerAdvice {

    @ModelAttribute
    public String setAttribute() {
        return "hello world";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public String exceptionHandler(NativeWebRequest request, Exception e) {
        return "error/500";
    }
}
