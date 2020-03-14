package thread;

public class JoinTest {
    /**
     * 测试join方法  阻塞线程
     * <p>
     * join：等待线程执行完成之后继续执行当前线程
     */

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();

        Thread thread1 = new Thread(testJoin);
        thread1.start();
        System.out.println(thread1.getName());

        Thread thread = new Thread(testJoin);
        System.out.println(thread.getName());
        thread.start();
        //等待线程执行完成之后继续执行当前线程（main）
        thread.join();
        System.out.println("等待线程终止");

        for (int i = 0; i < 11; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class TestJoin implements Runnable {

    @Override
    public void run() {

        Thread thread = Thread.currentThread();
        for (int i = 0; i < 11; i++) {
            System.out.println(thread.getName() + ":" + i);
        }
    }
}

