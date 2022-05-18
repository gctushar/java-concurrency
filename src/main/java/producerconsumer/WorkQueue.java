package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkQueue {

    private final int maxSizeOfWorkQueue;
    public Queue<Integer> worksToDo;
    Lock lock;
    Condition emptylock;
    Condition fulllock;


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
            lock.unlock();
            Thread.sleep(500);
            lock.lock();
            worksToDo.add(i);
            System.out.println("Created Work: " + worksToDo.toString());
            emptylock.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    int doWork() {
        lock.lock();
        int n = -4;
        try {
            while (worksToDo.size() == 0) {
                System.out.println("WAITING!! " + Thread.currentThread().getName() + " is waiting for job");
                emptylock.await();
            }

            n = worksToDo.poll();
            System.out.println(Thread.currentThread().getName() + " Completed Work: " + n);
            fulllock.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return n;
    }

}
