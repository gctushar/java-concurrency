package volatileatomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    AtomicInteger no = new AtomicInteger(0);

    public void increment() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            no.incrementAndGet();
        }
    }

    public static void main(String[] args) {

        AtomicExample atomicExample = new AtomicExample();

        Thread thread = new Thread(atomicExample::increment);

        Thread thread1 = new Thread(atomicExample::increment);
        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(atomicExample.no.get());
    }
}
