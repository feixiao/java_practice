/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package timer;

import java.time.LocalDate;
import java.util.TimerTask;
import java.util.Timer;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        Timer timer = new Timer();

        // 创建定时任务
        TimerTask timerTask = new TimerTask() {
            public void run() {
                System.out.println(LocalDate.now() + "-hello");
            }
        };

        // 添加调度
        timer.schedule(timerTask, 1000);

        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }
    }
}
