package taobq.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {

    final Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("test.xml");

        Object customer = classPathXmlApplicationContext.getBean("customer");

        System.out.println("阻塞前======");
        String name = Thread.currentThread().getName();
        System.out.println(name);

//        Thread.currentThread().join();

        /**
         *
         * join 会阻塞当前的主线程  等待其执行完才会执行其他线程
         * 但是不影响当前正在运行的其他线程
         *
         */
        ThreadTest t1 = new ThreadTest();
        ThreadTest t2 = new ThreadTest();

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        //thread1,thread2 线程并行执行

        thread1.start();
        thread2.start();
        thread1.join();

//        thread1.start();
//        thread1.join();//主线程要等到 thread线程执行结束之后才会执行

        System.out.println("阻塞后=====");
    }
}

class ThreadTest implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName());
        }

    }
}