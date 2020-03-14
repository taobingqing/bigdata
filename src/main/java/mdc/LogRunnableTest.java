package mdc;

//import com.chelaile.SpringStart;
//import com.chelaile.log.insert.LogRunnable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringStart.class)
public class LogRunnableTest {
    private Map<Integer, LogRunnable> logRunnableMap = new HashMap<>();

    @Test
    public void testLog() {
        ExecutorService logExecService = Executors.newFixedThreadPool(1);
        MDC.put("logFileName", "默认");
        MDC.put("logSign", "标记");

        for (int i = 0; i < 5; i++) {
            LogRunnable logRunnable = new LogRunnable("test" + i);
            logExecService.execute(logRunnable);
            logRunnableMap.put(i, logRunnable);
        }
        for (int i = 0; i < 5; i++) {
            LogRunnable logRunnable = logRunnableMap.get(i);
            logRunnable.setQueue("测试数据" + i);
            logRunnable.setQueue("我是测试" + i);
            logRunnable.setQueue("测试测试" + i);
            logRunnable.close();
        }
        while (true) {
        } //测试的时候，不加  while(true){}  的话，由于主线程结束，日志没有写入文件线程就终止了
    }
}
