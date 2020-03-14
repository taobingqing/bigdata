import com.google.common.base.Joiner;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * @author tbqY
 * @deprecated test02
 */
public class Test02 {


    public static void main(String[] args) throws IOException, InterruptedException {

        /**
         * getchars 的用法 切割字符串 0 （开始索引）4（结束索引）包左不包右 a (字符数组）0（目标数组中的起始偏移量）
         */
        String s = "aabb";
        char[] a = new char[4];
        s.getChars(0, 4,a,0);
        for (char c : a) {
            System.out.println(c);
        }

        //concat() 追加字符
        String concat = s.concat("|").concat("\\|");
        System.out.println(concat);

        /**
         * 拼接数组
         */
        //apache 的 common.lang的StringUtils
        String[] b ={"a","b","c"};
        String join = StringUtils.join(b, "|");
        System.out.println(join);


        //guava 的 Joiner
        String join1 = Joiner.on("|").join(b);
        System.out.println(join1);

        //处理空值,跳过
        String join2 = Joiner.on("-").skipNulls().join(1, null, 3);
        System.out.println(join2);

        //替换
        String none = Joiner.on("-").useForNull("none").join(2, 3, null, 4);
        System.out.println(none);


        /**
         * runtime 测试
         */
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec("D:\\工具\\jdk\\jd-gui.exe");
        InputStream fis=exec.getInputStream();
        //用一个读输出流类去读
        InputStreamReader isr=new InputStreamReader(fis);
        //用缓冲器读行
        BufferedReader br=new BufferedReader(isr);
        String line=null;
        //直到读完为止
        System.out.println("开始读取");
        while((line=br.readLine())!=null)
        {
            System.out.println("br 不等与 null");
            System.out.println(line);

        }
        System.out.println("读取结束");
        System.out.println(runtime);

        System.out.println("============================");
        while (true){
            System.out.println(new Random().nextInt(100));
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
