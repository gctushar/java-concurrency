package producerconsumer;

public class Producer implements Runnable {

    private WorkQueue workQueue;
    private Integer totalWorksToProduce;

    Producer(int totalWorksToProduce, WorkQueue workQueue){
        this.workQueue = workQueue;
        this.totalWorksToProduce = totalWorksToProduce;
    }

    @Override
    public void run() {

      for (int i = 0; i< totalWorksToProduce; i++){
          System.out.println("Creating Work: " + i);
          workQueue.addWork(i);
      }

    }
}
