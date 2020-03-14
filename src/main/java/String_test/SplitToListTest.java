package String_test;

import com.google.common.base.Splitter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;

public class SplitToListTest {


    /**
     * 将字符串（没有空值的）按照自定义分隔符进行拆分成list数组
     * 存在问题就是：hello,world中间逗号前后不能加空格
     */
    @Test
    public void testSplitOn() {
        List<String> list1 = Splitter.on(",").splitToList("hello,world");
//        assertThat(list, notNullValue());
//        assertThat(list.size(), equalTo(2));
//        assertThat(list.get(0), equalTo("hello"));
//        assertThat(list.get(1), equalTo("world"));

        System.out.println(list1);
    }


    /**
     * 将字符串（有空值且分隔符之间无空白）按照自定义分隔符进行拆分成list数组
     * 存在问题就是：hello,world中间逗号前后不能加空格
     * 出现异常，因为长度不是2而是5
     */

    @Test
    public void testSplitOnEmpty() {
        List<String> list2 = Splitter.on(",").splitToList("hello,world,,'',null");
//        assertThat(list, notNullValue());
//        assertThat(list.size(), equalTo(2));
        System.out.println(list2);
    }

    /**
     * 将字符串（有空值且分隔符之间无空白）按照自定义分隔符进行拆分成list数组（刨除空值）
     * 存在的问题就是：空白还是无法解决，但是能把空值（,,）去掉
     * 所以长度是4，程序正常
     */
    @Test
    public void testSplitOnOmitEmpty() {
        List<String> list3 = Splitter.on(",").omitEmptyStrings().splitToList("hello,world,,'',null");
//        assertThat(list, notNullValue());
//        assertThat(list.size(), equalTo(4));
        System.out.println(list3);
    }

    /**
     * 将字符串（有空值且分隔符之间无空白）按照自定义分隔符进行拆分成list数组（刨除空值和空白）
     */
    @Test
    public void testSplitOnOmitEmptyAndTrim() {
        List<String> list4 = Splitter.on(",").trimResults().omitEmptyStrings().splitToList("hello, world, ,'', null");
//        assertThat(list, notNullValue());
////        assertThat(list.size(), equalTo(4));
////        assertThat(list.get(0), equalTo("hello"));
////        assertThat(list.get(1), equalTo("world"));
////        assertThat(list.get(2), equalTo("''"));
////        assertThat(list.get(3), equalTo("null"));

        System.out.println(list4);

        Iterator<String> iterator = list4.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * 将字符串按照字符长度进行拆分成list数组
     * <p>
     * 比如：aaaabbbbccccdddd
     * 结果：
     * aaaa
     * bbbb
     * cccc
     * dddd
     */
    @Test
    public void testSplitFixLength() {
        List<String> list5 = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
//        assertThat(list, notNullValue());
//        assertThat(list.size(), equalTo(4));
//        assertThat(list.get(0), equalTo("aaaa"));
//        assertThat(list.get(1), equalTo("bbbb"));
//        assertThat(list.get(2), equalTo("cccc"));
//        assertThat(list.get(3), equalTo("dddd"));
        System.out.println(list5);
    }

    /**
     * 将字符串按照自定义字符进行拆分成list数组，并且限制截取list的个数
     * <p>
     * 比如：on("#").limit(3)。按照#分割，分割出三个长度。
     * A#B#C#D#E#F
     * 结果：
     * A
     * B
     * C#D#E#F
     */
    @Test
    public void testSplitOnSplitLimit() {
        List<String> list6 = Splitter.on("#").limit(3).splitToList("A#B#C#D#E#F");
//        assertThat(list, notNullValue());
//        assertThat(list.size(), equalTo(3));
//        assertThat(list.get(0), equalTo("A"));
//        assertThat(list.get(1), equalTo("B"));
//        assertThat(list.get(2), equalTo("C#D#E#F"));
        System.out.println(list6);
    }

    /**
     * 按照正则表达式分割一
     */
    @Test
    public void testSplitOnPatternString() {
        List<String> list7 = Splitter.onPattern("\\/").trimResults().omitEmptyStrings().splitToList("A / B / C / / ///");
        System.out.println(list7);
    }

    /**
     * 按照正则表达式分割二
     */
    @Test
    public void testSplitOnPattern() {
        List<String> list8 = Splitter.on(Pattern.compile("\\/")).trimResults().omitEmptyStrings().splitToList("A / B / C / / ///");
        System.out.println(list8);
    }

    /**
     * 按照某字符进行左右切割成map
     */
    @Test
    public void testSplitOnSplitToMap() {
        Map<String, String> map = Splitter.on(Pattern.compile("\\/")).trimResults().omitEmptyStrings()
                .withKeyValueSeparator("=").split("key=value / key1=value1 / key2=valu2 / / ///");

        System.out.println(map);
    }
}
