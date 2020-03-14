package String_Utils_test;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

public class JoinTest {
    public static void main(String[] args) {
        String basePath = StringUtils.join("/", "kfka", "/");
        System.out.println(basePath);

        String s = "  java ";
        String trim = s.trim();
        System.out.println(s);
        System.out.println(trim);

        String join = StringUtils.join("a", "b");
        System.out.println(join);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("线程中断  3 秒");
        }

        System.out.println("线程重新启动");

    }
}
