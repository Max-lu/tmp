package cn.maxlu.demo.spring.async;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by luwei on 2017/7/14.
 */
public class AsyncTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/async/spring-context.xml");
        context.start();
        AsyncJob asyncJob = context.getBean(AsyncJob.class);
        long start = System.currentTimeMillis();
        doJobWithReturn(asyncJob);
        long end = System.currentTimeMillis();
        System.out.println("cost " + (end - start) / 1000 + " s");

        System.exit(0);
    }

    private static void doJobWithoutReturn(AsyncJob asyncJob) {
        asyncJob.doJobWithoutReturn();
    }

    private static void doJobWithReturn(AsyncJob asyncJob) {
        Future<String> future = asyncJob.doJobWithReturn();

        while (!future.isDone()) {
            System.out.println("wait for result");
        }

        try {
            System.out.println("result:" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
