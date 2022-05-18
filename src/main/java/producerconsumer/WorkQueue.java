package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkQueue {

    public Queue<Integer> worksToDo;
    Lock lock;
    Condition emptylock;
    Condition fulllock;
    private final int maxSizeOfWorkQueue;


    WorkQueue(int maxSizeOfWorkQueue) {
        worksToDo = new LinkedList<Integer>();
        this.maxSizeOfWorkQueue = maxSizeOfWorkQueue;
        lock = new ReentrantLock();
        emptylock = lock.newCondition();
        fulllock = lock.newCondition();
    }

    void addWork(Integer i) {
        lock.lock();
        try {
            while (worksToDo.size() == maxSizeOfWorkQueue) {

                fulllock.await();
            }

            worksToDo.add(i);
            System.out.println("Created Work: " + worksToDo.toString());
            emptylock.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    int doWork() {
        lock.lock();
        while (worksToDo.size() == 0) {
            try {
                emptylock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Thread.currentThread().isInterrupted()) {
                return -4;
            }
        }
        int n = worksToDo.poll();
        System.out.println(Thread.currentThread().getName() + " Completed Work: " + n);
        fulllock.signalAll();
        lock.unlock();
        return n;


    }

}
