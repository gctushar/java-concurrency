package producerconsumer;

import threadlocal.UserService;

public class Application {

    public static void main(String[] args) {

        WorkQueue workQueue = new WorkQueue(5);

        Consumer consumer1 = new Consumer(10,workQueue);
        Consumer consumer2 = new Consumer(5,workQueue);
        Producer producer = new Producer(10,workQueue);


        Thread pThread = new Thread(producer);
        pThread.setName("producer");
        Thread cThread1 = new Thread(consumer1);
        cThread1.setName("cThread1");
        Thread cThread2 = new Thread(consumer2);
        cThread2.setName("cThread2");


        pThread.start();
        cThread1.start();
        cThread2.start();

        System.out.println("_______________________________");

        try {
            Thread.sleep(10000);
            cThread1.interrupt();
            cThread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
