package cn.maxlu.demo.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by luwei on 2017/7/14.
 */
@Service
class AsyncJob {

    @Async
    void doJobWithoutReturn() {
        try {
            Thread.sleep(10 * 1000);
            System.out.println("job done...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    Future<String> doJobWithReturn() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("success");
    }
}
