package thread.thread_safe;

import java.util.concurrent.CountDownLatch;

/**
 * ountDownLatch是一个同步的辅助类，它可以允许一个或多个线程等待，直到一组在其它线程中的操作执行完成。
 * 一个CountDownLatch会通过一个给定的count数来被初始化。其中await()方法会一直阻塞，
 * 直到当前的count被减到0，而这个过程是通过调用countDown()方法来实现的。
 * 在await()方法不再阻塞以后，所有等待的线程都会被释放，并且任何await()的子调用都会立刻返回。
 * 这是一次性的－－count不能被重置。如果你需要一种能重置count的版本，请考虑使用CyclicBarrier。
 * CountDownlatch是一个多功能的同步工具，可以被用于各种目的。
 * 一个CountDownLatch通过一个值为1的count被初始化，来作为一个开/关的门或门闩：
 * 所有调用了await()的线程都会在门前等待，直到门被一个线程通过调用countDown()打开。
 * 一个被初始化为N的CountDownLatch可以被用来“在N个线程都完成了某种操作（或者一些操作已经被完成了N次）之后创建一个线程”。
 * CountDownLatch一个有用的属性就是它不需要线程们在继续执行之前，调用countDown来等待count被减到0。
 * 它简单地阻止了任何调用了await()的线程继续，直到所有的线程都能够通过。
 *
 * https://www.jianshu.com/p/1ec1009ebab7
 */
public class CountDownLatchDriver {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        // 依次创建并启动5个worker线程
        for (int i = 0; i < 5; ++i) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        System.out.println("Driver is doing something...");
        System.out.println("Driver is Finished, start all workers ...");
        startSignal.countDown(); // Driver执行完毕，发出开始信号，使所有的worker线程开始执行
        doneSignal.await(); // 等待所有的worker线程执行结束
        System.out.println("Finished.");
    }
}

class Worker implements Runnable{
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            startSignal.await(); // 等待Driver线程执行完毕，获得开始信号
            System.out.println("Working now ...");
            doneSignal.countDown(); // 当前worker执行完毕，释放一个完成信号
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
