package executorServiceExample;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {


    public static void main(String[] args) {
        Task task = new Task();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ExecutorService cashExecutorService = Executors.newCachedThreadPool();
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();


        for (int i = 0; i < 100; i++) {
            System.out.println(i);
//            executorService.execute(new Task());
            cashExecutorService.execute(new Task());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("------------------------------------------------------------------");

        executorService.shutdown();
        System.out.println("Thread Name: " + Thread.currentThread().getName());

    }

}

class Task implements Runnable{

    @Override
    public void run() {

        System.out.println("Thread Name : " + Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
