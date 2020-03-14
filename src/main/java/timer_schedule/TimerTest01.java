package timer_schedule;



import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Timer;
import java.util.TimerTask;


public class TimerTest01 {
    private static Logger logger = Logger.getLogger(TimerTest01.class);

    public static void main(String[] args) {


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前线程："+Thread.currentThread().getName());
                System.out.println("定时时间到");
            }
        },3*1000,3*1000);

        System.out.println("main  thread");

        TimerTest01 timerTest01 = new TimerTest01();
        timerTest01.prepare();

    }


    private void prepare() {

        // executor内存限速控制
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
                MemoryUsage memoryUsage = bean.getHeapMemoryUsage();
                long used = (int) (memoryUsage.getUsed() / 1024 / 1024.0f);
                long max = (int) (memoryUsage.getMax() / 1024 / 1024.0f);
                if (used >= max * 0.2) {
                    logger.warn("Thread " + Thread.currentThread().getName() + " Mem Used " + used + " mb Max " + max + " mb");
                    System.out.println("不符合  条件");
                } else {
//                    System.out.println("内存 已用 ："+used);
//                    System.out.println("内存 最大 ："+max);
                    System.out.println("Thread " + Thread.currentThread().getName() + " Mem Used " + used + " mb Max " + max + " mb");
                    System.out.println("符合  条件");
                }
            }
        }, 1000, 1000);
    }

}

