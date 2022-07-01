package volatileatomic;

import java.util.concurrent.ConcurrentLinkedQueue;

public class VolatileExample {

    private volatile int no = 5;

    public void increment() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            int x = no;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            no = x + 1;
        }
    }

    public static void main(String[] args) {

        VolatileExample volatileExample = new VolatileExample();

        Thread thread = new Thread(volatileExample::increment);

        Thread thread1 = new Thread(volatileExample::increment);
        thread.start();
        thread1.start();



        try {
            thread.join();
            thread1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(volatileExample.no);

    }
}
