package guava_test;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.HashMap;

public class ImutableMapTest {

    @Test
    public void test01() {
        ImmutableMap<Object, Object> map = ImmutableMap.of();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(1, 2);
        hashMap.put(2, 2);
        hashMap.put(3, 2);
        hashMap.put(4, 2);
        map = ImmutableMap.copyOf(hashMap);
        System.out.println(map); //  {1=2, 2=2, 3=2, 4=2}
        ImmutableMap<String, String> of = ImmutableMap.of("a", "b", "c", "d");
        System.out.println(of); // {a=b, c=d}
//        map.put(2, 3);    会报错

    }
}
