package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Future_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Future_Callable().test();

        new Future_Callable().test02();

    }

    public void test() throws ExecutionException, InterruptedException {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        new Thread(ft, "有返回值的线程").start();
        System.out.println("子线程的返回值" + ft.get());
    }

    public void test02() {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 3, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy());

        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
//        for (int i = 0; i < 3; i++) {
//            //这样做是启动了三个线程 等于跑了三遍
//            Future<Integer> future = executorService.submit(new CallableThreadTest());
//            futures.add(future);
//        }

        Future<Integer> future = executorService.submit(new CallableThreadTest());

        for(Future f : futures){
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭线程池
        executorService.shutdownNow();

    }

    class CallableThreadTest implements Callable<Integer> {

        @Override
        public Integer call() {
            int i;
            for (i = 0; i < 100; i += 2) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            return i;
        }
    }
}
