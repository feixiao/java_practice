package CyclicBarrier;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TaskRunner implements Runnable {
    private final CyclicBarrier barrier;

    private static final int NUM_TASKS = 10;

    public TaskRunner(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TASKS; i++) {
            // Perform task
        }

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}