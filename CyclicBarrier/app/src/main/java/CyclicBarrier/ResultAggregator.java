
package CyclicBarrier;

public class ResultAggregator implements Runnable {
    @Override
    public void run() {
        System.out.println("All tasks complete! Aggregating results...");
        // Aggregate results from tasks
    }
}