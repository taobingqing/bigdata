package thread_exchange;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String a = "我买了鸡翅包饭！";
                    System.out.println(Thread.currentThread().getName() + "说：" + a);
                    try {
                        System.out.println(Thread.currentThread().getName() + "说：等着b买汉堡！");

                        String b = exchanger.exchange(a);
                        System.out.println(Thread.currentThread().getName() + "说：我拿到了汉堡！");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                String a = "我买了鸡翅包饭！";
//                System.out.println(Thread.currentThread().getName() + "说：" + a);
//                try {
//                    System.out.println(Thread.currentThread().getName() + "说：等着b买汉堡！");
//                    TimeUnit.SECONDS.sleep(5);
//                    String b = exchanger.exchange(a);
//                    System.out.println(Thread.currentThread().getName() + "说：我拿到了汉堡！");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String b = "我买了汉堡！";
                System.out.println(Thread.currentThread().getName() + "说：" + b);

                try {
                    System.out.println(Thread.currentThread().getName() + "说：现在我们来交换吧！");
                    String a = exchanger.exchange(b);
                    //把第一个线程中 a 变量拿了过来
                    System.out.println(Thread.currentThread().getName() + "说我拿到了鸡翅包饭！");
                    System.out.println(Thread.currentThread().getName() + "说：" + a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}