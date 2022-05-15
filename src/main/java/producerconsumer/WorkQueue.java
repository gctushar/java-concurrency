package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkQueue {

    public Queue<Integer> worksToDo;
    private int maxSizeOfWorkQueue;
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

    void addWork(Integer i)  {
        lock.lock();
        while (worksToDo.size() == maxSizeOfWorkQueue) {
            try {
                fulllock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        worksToDo.add(i);
        System.out.println("Created Work: " + worksToDo.peek());
        emptylock.signalAll();
        lock.unlock();

    }

    int doWork() {
        lock.lock();
        while (worksToDo.size() == 0) {
            try {
                emptylock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int n = worksToDo.poll();
        System.out.println(Thread.currentThread().getName() + " Completed Work: " + n);
        fulllock.signalAll();
        lock.unlock();
        return n;

    }

}
