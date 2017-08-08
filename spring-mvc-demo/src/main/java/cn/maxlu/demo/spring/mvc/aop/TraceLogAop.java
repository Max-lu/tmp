package cn.maxlu.demo.spring.mvc.aop;

import cn.maxlu.demo.spring.mvc.controller.HelloWorldController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by luwei on 2017/6/26.
 */

@Aspect
@Component
public class TraceLogAop {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ConcurrentHashMap<Class, Logger> LOGGER_MAP = new ConcurrentHashMap<>();

    @Pointcut("execution(* cn.maxlu.demo.spring.mvc.controller..*.*(..))")
    public void demoAspect() {
        //should keep empty here
    }

    @Before("demoAspect()")
    public void doBefore(JoinPoint joinPoint) {
        Logger logger = getLogger(joinPoint.getTarget().getClass());
        Signature methodSign = joinPoint.getSignature();
        logger.trace(methodSign);
//        logger.trace(methodSign + "" + joinPoint.getSourceLocation().getLine());  can't getLine in spring aop
    }

    private Logger getLogger(Class clazz) {
        //todo use softReference
        Logger logger = LOGGER_MAP.get(clazz);
        if (logger == null) {
            LOGGER_MAP.putIfAbsent(clazz, LogManager.getLogger(clazz));
        }
        return LOGGER_MAP.get(clazz);
    }

    @AfterThrowing(pointcut = "demoAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    }

    @After("demoAspect()")
    public void doAfter(JoinPoint joinPoint) {

    }

    @Around("demoAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object retVal = joinPoint.proceed();
        return retVal;
    }

}
