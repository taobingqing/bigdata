package thread.map_thread_safe;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Map线程安全的测试
 * 总结:
 */
public class MapTest {

    public static final int THREAD_COUNT = 1;
    public static final int MAP_SIZE = 1000;
    public static final int EXECUTION_MILLES = 1000;
    public static final int[] KEYS = new int[100];

    public static void main(String[] args) throws InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        //创建线程
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i]  = new SynchronizedThread();//执行get次数和时间 : 37116922  (1004) ms
//			threads[i] = new LockThread();//执行get次数和时间 : 43899435  (1003) ms
//			threads[i] = new ConcurrentThread();//执行get次数和时间 : 90580822  (1004) ms
            threads[i].start();
        }

        //等待其他线程执行若干时间
        Thread.sleep(EXECUTION_MILLES);

        //统计get操作的次数
        long sum = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            sum += threads[i].getClass().getDeclaredField("count").getLong(threads[i]);
        }

        long timeCost = System.currentTimeMillis() - start;
        System.out.println(sum + "  (" + timeCost + ") ms");

        System.exit(0);

    }

    /**
     * 填充map
     * @throws InterruptedException
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void fillMap(Map<Integer,Integer> map) {
        Random random = new Random();
        for (int i = 0; i < MAP_SIZE; i++) {
            map.put(random.nextInt(),random.nextInt());
        }


    }


}

class ConcurrentThread extends Thread {
    private static Map<Integer,Integer> map = new ConcurrentHashMap<Integer, Integer>();
    public long count = 0;
    static {
        MapTest.fillMap(map);
    }

    @Override
    public void run() {


        for(;;count++) {
            int index = (int) (count % MapTest.KEYS.length);
            map.get(MapTest.KEYS[index]);
        }


    }

}

/**
 * 同步代码块实现线程安全的MAP
 */
class SynchronizedThread extends Thread {
    private static Map<Integer,Integer> map = new HashMap<Integer, Integer>();
    public long count = 0;
    static {
        MapTest.fillMap(map);
    }


    @Override
    public void run() {

        for(;;count++) {
            int index = (int) (count% MapTest.KEYS.length);
            synchronized(SynchronizedThread.class) {
                map.get(MapTest.KEYS[index]);
            }
        }

    }

}

/**
 * Lock实现线程安全的map
 */
class LockThread extends Thread {

    private static Map<Integer,Integer> map = new HashMap<Integer,Integer>();

    private static Lock lock = new ReentrantLock();

    public long count = 0;

    static {
        MapTest.fillMap(map);
    }

    @Override
    public void run() {

        for(;;count++) {

            int index = (int) (count % MapTest.KEYS.length);
            lock.lock();
            map.get(MapTest.KEYS[index]);
            lock.unlock();
        }

    }


}