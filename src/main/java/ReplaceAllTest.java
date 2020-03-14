import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

public class ReplaceAllTest {
    public static void main(String[] args) {

        //replaceall 第二个参数是替换值,和replace 方法结果一样
        String replaceAll = "aaaabbb.aaa".replaceAll("a", "c");
        System.out.println(replaceAll); //ccccbbb.ccc

        String[] a = {"1","2"};



    }

    @Test
    public void test01(){
        Splitter splitter = Splitter.on("|").trimResults();
        List<String> list = splitter.splitToList("a|b|c||||d||||");
        System.out.println(list.size());
    }
}
