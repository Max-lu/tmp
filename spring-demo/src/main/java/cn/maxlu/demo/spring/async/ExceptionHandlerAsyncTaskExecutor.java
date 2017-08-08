package cn.maxlu.demo.spring.async;

import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by luwei on 2017/7/14.
 * 自定义异步执行异常处理执行器
 */
public class ExceptionHandlerAsyncTaskExecutor implements AsyncTaskExecutor {

    private AsyncTaskExecutor executor;

    public ExceptionHandlerAsyncTaskExecutor(AsyncTaskExecutor executor) {
        this.executor = executor;
    }

    //用独立的线程来包装，@Async其本质就是如此
    @Override
    public void execute(Runnable task) {
        executor.execute(createWrappedRunnable(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        executor.execute(createWrappedRunnable(task), startTimeout);
    }

    @Override
    public Future submit(Runnable task) {
        return executor.submit(createWrappedRunnable(task));
    }

    @Override
    public <T> Future<T> submit(final Callable<T> task) {
        return executor.submit(createCallable(task));
    }

    private <T> Callable<T> createCallable(final Callable<T> task) {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                try {
                    return task.call();
                } catch (Exception ex) {
                    handle(ex);
                    throw ex;
                }
            }
        };
    }

    private Runnable createWrappedRunnable(final Runnable task) {
        return new Runnable() {
            public void run() {
                try {
                    task.run();
                } catch (Exception ex) {
                    handle(ex);
                }
            }
        };
    }

    private void handle(Exception ex) {
        //具体的异常逻辑处理的地方
        System.err.println("Error during @Async execution: " + ex);
    }
}
