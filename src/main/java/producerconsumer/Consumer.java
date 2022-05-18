package producerconsumer;

public class Consumer implements Runnable {

    private WorkQueue workQueue;
    private int totalWorksToConsume;

    Consumer(int totalWorksToConsume, WorkQueue workQueue) {
        this.workQueue = workQueue;
        this.totalWorksToConsume = totalWorksToConsume;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalWorksToConsume; i++) {
            System.out.println(Thread.currentThread().getName() + " Starting work: " );
            workQueue.doWork();
            try {
                Thread.sleep(100);
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted by main thread.............." );
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
