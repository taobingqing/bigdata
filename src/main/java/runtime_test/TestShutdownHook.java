package runtime_test;

import java.util.concurrent.TimeUnit;

public class TestShutdownHook {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 定义线程1
        Thread thread1 = new Thread() {

            public void run() {
                for(int i = 0;i<10;i++){
                    System.out.println("thread1..." + i);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // 定义线程2
        Thread thread2 = new Thread() {
            public void run() {
                for(int i = 0;i<10;i++){
                    System.out.println("thread2..." + i);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        // 定义关闭线程
        Thread shutdownThread = new Thread() {
            public void run() {
                System.out.println("shutdownThread...");
            }
        };

        // jvm关闭的时候先执行该线程钩子
        Runtime.getRuntime().addShutdownHook(shutdownThread);

        thread1.start();
        thread2.start();
    }
}
